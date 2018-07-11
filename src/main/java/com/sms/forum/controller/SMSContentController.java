package com.sms.forum.controller;

import com.sms.forum.model.SMSContent;
import com.sms.forum.model.Telephone;
import com.sms.forum.model.Uploader;
import com.sms.forum.result.ApiResponse;
import com.sms.forum.result.ResponseCode;
import com.sms.forum.service.ISMSContentService;
import com.sms.forum.service.ITelService;
import com.sms.forum.service.IUploaderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping("/smscontent")
public class SMSContentController {

    private final int TELEPHONE_NUM = 10;

    @Resource
    private ITelService telService;

    @Resource
    private IUploaderService uploaderService;

    @Resource
    private ISMSContentService smsContentService;

    @RequestMapping(value = "/getSMSContent", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Telephone>> getSMSContent() {
        ApiResponse<List<Telephone>> response = new ApiResponse<List<Telephone>>();
        //优先获取当前的uploader，根据上传者和上传时间，获取电话号码
        try {
            List<Telephone> telephones = Collections.emptyList();
            List<Uploader> uploaders = uploaderService.selectAllUploaders();//查找的是可用的
            for (Uploader uploader : uploaders) {
                List<Telephone> phones = telService.selectTelByUploader(uploader.getUsername());
                telephones.addAll(phones);
                if (telephones.size() > TELEPHONE_NUM) {
                    break;
                }
            }
            response.data = telephones;
        } catch (Exception e) {
            response.data = null;
            response.code = -1;
            response.msg = "exception";
        }
        return response;
    }

    @RequestMapping(value = "/uploadSMSContent", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<Integer> uploadContentAndTel(String content, String mobiles, String uploader) {
        ApiResponse<Integer> response = new ApiResponse<Integer>();
        //将内容插入到 数据库中；将电话号码也插入到数据库中
        Date currentDate = new Date();
        SMSContent smsContent = new SMSContent();
        smsContent.setContent(content);
        smsContent.setUploader(uploader);
        smsContent.setUploadTime(currentDate);
        try {
            response.data = smsContentService.insertSmsContent(smsContent);
        } catch (Exception e) {
            e.printStackTrace();
            response.code = ResponseCode.INSERT_TEL_EX;
            response.msg = "insert sms content exception";
        }
        List<String> tels = Arrays.asList(mobiles.split(",|\n"));
        List<Telephone> telephones = new ArrayList<Telephone>();
        for (String tel : tels) {
            Telephone telephone = new Telephone();
            telephone.setSended(0);
            telephone.setTelephone(tel);
            telephone.setUploader(uploader);
            telephone.setUploadTime(currentDate);
            telephones.add(telephone);
        }
        try {
            if (response.code != -1) {
                telService.insertTelephones(telephones);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.code = ResponseCode.INSERT_CONTENT_EX;
            response.msg = "insert telephone exception";
        }
        return response;
    }
}
