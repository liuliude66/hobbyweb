package com.sms.forum.controller;

import com.sms.forum.model.Uploader;
import com.sms.forum.result.ApiResponse;
import com.sms.forum.service.IUploaderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/uploader")
public class UploaderController {

    @Resource
    private IUploaderService uploaderService;

    @RequestMapping(value = "/getUploaders", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<Uploader>> getUploaders() {
        ApiResponse<List<Uploader>> result = new ApiResponse<List<Uploader>>();
        try {
            result.msg = "success";
            result.data = uploaderService.selectAllUploaders();//查找的是可用的
        } catch (Exception e) {
            result.code = -1;
            result.msg = "exception";
        }
        return result;
    }
}
