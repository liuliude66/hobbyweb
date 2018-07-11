package com.sms.forum.dao;

import com.sms.forum.model.Uploader;

import java.util.List;

public interface UploaderDao {
    List<Uploader> selectAllUploaders();
}
