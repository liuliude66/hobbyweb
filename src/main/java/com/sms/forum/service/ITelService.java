package com.sms.forum.service;

import com.sms.forum.model.Telephone;

import java.util.List;

public interface ITelService {
    List<Telephone> selectTelByUploader(String uploader);
    int insertTelephones(List<Telephone> telephones);
}
