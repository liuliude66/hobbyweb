package com.sms.forum.service.impl;

import com.sms.forum.dao.SMSContentDao;
import com.sms.forum.model.SMSContent;
import com.sms.forum.service.ISMSContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SMSContentServiceImpl implements ISMSContentService {

    @Resource
    private SMSContentDao smsContentDao;

    public List<SMSContent> selectSmsContentByUploader(String uploader) {
        return smsContentDao.selectSmsContentByUploader(uploader);
    }

    public int insertSmsContent(SMSContent smsContent) {
        return smsContentDao.insertSmsContent(smsContent);
    }
}
