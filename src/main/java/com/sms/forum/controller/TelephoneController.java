package com.sms.forum.controller;

import com.sms.forum.model.Telephone;
import com.sms.forum.model.Uploader;
import com.sms.forum.service.ITelService;
import com.sms.forum.service.IUploaderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/telephone")
public class TelephoneController {

    private final int TELEPHONE_NUM = 10;

    @Resource
    private ITelService telService;

    @Resource
    private IUploaderService uploaderService;

    @RequestMapping(value = "/getTelephones", method = RequestMethod.GET)
    @ResponseBody
    public List<Telephone> getTelephones() {
        //优先获取当前的uploader，根据上传者和上传时间，获取电话号码
        List<Uploader> uploaders = uploaderService.selectAllUploaders();//查找的是可用的
        List<Telephone> telephones = new ArrayList<Telephone>();
        for (Uploader uploader : uploaders) {
            if (uploader.getEnabled() == 0) {
                continue;
            }
            List<Telephone> phones = telService.selectTelByUploader(uploader.getUsername());
            telephones.addAll(phones);
            if (telephones.size() > TELEPHONE_NUM) {
                break;
            }
        }
        return telephones;
    }
}
