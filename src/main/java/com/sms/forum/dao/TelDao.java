package com.sms.forum.dao;

import com.sms.forum.model.Telephone;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelDao {
    List<Telephone> selectTelByUploader(String uploader);

    int insertTelephones(List<Telephone> telephones);
}
