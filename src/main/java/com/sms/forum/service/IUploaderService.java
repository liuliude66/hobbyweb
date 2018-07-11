package com.sms.forum.service;

import com.sms.forum.model.Uploader;

import java.util.List;

public interface IUploaderService {
    List<Uploader> selectAllUploaders();
}
