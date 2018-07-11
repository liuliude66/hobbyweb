package com.sms.forum.controller;

import com.sms.forum.mem.MemcacheManager;
import com.sms.forum.model.Account;
import com.sms.forum.model.SessionModel;
import com.sms.forum.result.ApiResponse;
import com.sms.forum.result.ResponseCode;
import com.sms.forum.service.IAccountService;
import com.sms.forum.utils.SessionHelper;
import com.sms.forum.utils.TimeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final static int SESSION_EXT = 3600 * 24;

    //private Logger log = Logger.getLogger(AccountController.class);

    @Resource
    private IAccountService accountService;

    @RequestMapping("/getAccounts")
    @ResponseBody
    public ApiResponse<List<Account>> getAccounts(HttpServletRequest request, String username) {
        //log.info("查询所有用户信息");
        ApiResponse<List<Account>> response = new ApiResponse<List<Account>>();
        String headerSession = request.getHeader("sessionid");
        if (!SessionHelper.verifySession(headerSession, username)) {
            response.code = ResponseCode.SESSION_ERROR;
            response.msg = "登录过期，请重新登录";
        } else {
            response.data = accountService.selectAllUser();
        }
        return response;//accountService.selectAllUser();
    }

    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccountInfo(@RequestParam("id") long id) {
        return accountService.getUserById(id);
    }

    @RequestMapping(value = "/getAccountInfo2", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccountInfo(@RequestParam("username") String username) {
        return accountService.getAccountByUsername(username);
    }

    @RequestMapping(value = "/distribute.do")
    @ResponseBody
    public ApiResponse<SessionModel> distribute(HttpServletRequest request) {
        //清除原来的缓存，并且生成并缓存最新的缓存session
        ApiResponse<SessionModel> response = new ApiResponse<SessionModel>();
        String sessionid = request.getHeader("sessionid");
        System.out.println("session-id------->" + sessionid);

        if (!StringUtils.isEmpty(sessionid) && StringUtils.isEmpty(MemcacheManager.get().get("temp-" + sessionid))) {
            MemcacheManager.get().remove("temp-" + sessionid);
        }
        //Enumeration<String> headers = request.getHeaderNames();
        //生成新的session值
        String xReqWith = request.getHeader("x-requested-with");
        String ua = request.getHeader("user-agent");

        SessionModel data = new SessionModel();
        data.sessionId = SessionHelper.generateSession(xReqWith, ua);
        data.expiryTime = Long.valueOf(TimeUtils.timeStamp());
        response.data = data;

        return response;
    }

    @RequestMapping(value = "/check_status.do")
    @ResponseBody
    public ApiResponse<Account> checkStatus(HttpServletRequest request) {
        ApiResponse<Account> response = new ApiResponse<Account>();
        String sessionid = request.getHeader("sessionid");//用户传入的
        Object sessionCacheObj = MemcacheManager.get().get(sessionid);
        if (sessionCacheObj != null) {
            Account account = (Account) sessionCacheObj;
            String username = account.getUsername();
            if (username != null) {
                //获取用户的的当前最新信息
                response.data = account;
            } else {
                response.data = null;
                response.code = ResponseCode.LOGIN_ERROR_ONLY;
                response.msg = "只能在一台设备上登录啊";
            }
        } else {
            response.data = null;
            response.code = ResponseCode.LOGIN_ERROR_ONLY;
            response.msg = "只能在一台设备上登录哦，亲";
        }
        return response;
    }

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public ApiResponse<Account> login(HttpServletRequest request, @RequestBody Account act) {//@RequestParam("username"), String username, String password
        ApiResponse<Account> result = new ApiResponse<Account>();
        String username = act.getUsername();//request.getParameter("username");
        if (StringUtils.isEmpty(username) || username.length() < 5) {
            result.code = ResponseCode.LOGIN_ERROR_NULL;
            result.msg = "请输入正确的用户账号信息";
            result.data = null;
            return result;
        }
        String password = act.getPassword();//request.getParameter("password");
        if (StringUtils.isEmpty(password) || password.length() < 5) {
            result.code = ResponseCode.LOGIN_ERROR_NULL;
            result.msg = "请输入正确的用户密码信息";
            result.data = null;
            return result;
        }
        //Enumeration<String> headers = request.getHeaderNames();
        Account account = accountService.getAccountByUsername(username);
        if (account == null) {
            result.code = ResponseCode.LOGIN_ERROR_USERNAME;
            result.msg = "该账号不存在";
            result.data = null;
            return result;
        }
        if (!account.getPassword().equals(password)) {
            result.code = ResponseCode.LOGIN_ERROR_PASSWORD;
            result.msg = "请输入正确的密码";
            result.data = null;
            return result;
        }

        String sessionid = request.getHeader("sessionid");
        //将session写入缓存
        Object tempSession = MemcacheManager.get().get(sessionid);
        System.out.println(TimeUtils.timeStamp2Date(TimeUtils.timeStamp(), "yyyy-MM-dd HH:mm:ss") + "--tempSession---->" + tempSession);
        if (!ObjectUtils.isEmpty(tempSession)) {
            MemcacheManager.get().update(username, account.getUsername(), SESSION_EXT);
        } else {
            MemcacheManager.get().add(username, account.getUsername(), SESSION_EXT);
        }
        //验证session 有效期
        result.data = account;
        return result;
    }
}
