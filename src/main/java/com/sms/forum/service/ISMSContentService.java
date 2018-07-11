package com.sms.forum.service;

import com.sms.forum.model.SMSContent;

import java.util.List;

public interface ISMSContentService {

    List<SMSContent> selectSmsContentByUploader(String uploader);
    int insertSmsContent(SMSContent smsContent);

}
