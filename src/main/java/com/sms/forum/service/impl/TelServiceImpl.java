package com.sms.forum.service.impl;

import com.sms.forum.dao.TelDao;
import com.sms.forum.model.Telephone;
import com.sms.forum.service.ITelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class TelServiceImpl implements ITelService {

    @Resource
    private TelDao telDao;

    public List<Telephone> selectTelByUploader(String uploader) {
        return telDao.selectTelByUploader(uploader);
    }

    public int insertTelephones(List<Telephone> telephones){
        return telDao.insertTelephones(telephones);
    }
}
