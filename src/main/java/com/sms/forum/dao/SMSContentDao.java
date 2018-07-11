package com.sms.forum.dao;

import com.sms.forum.model.SMSContent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SMSContentDao {
    List<SMSContent> selectSmsContentByUploader(@Param("uploader") String uploader);

    int insertSmsContent(SMSContent smsContent);
}
