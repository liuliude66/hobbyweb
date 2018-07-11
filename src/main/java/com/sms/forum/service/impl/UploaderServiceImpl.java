package com.sms.forum.service.impl;

import com.sms.forum.dao.UploaderDao;
import com.sms.forum.model.Uploader;
import com.sms.forum.service.IUploaderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UploaderServiceImpl implements IUploaderService {

    @Resource
    private UploaderDao uploaderDao;

    public List<Uploader> selectAllUploaders() {
        return uploaderDao.selectAllUploaders();
    }
}
