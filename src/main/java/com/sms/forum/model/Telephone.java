package com.sms.forum.model;

import java.util.Date;

public class Telephone {

    private long id;
    private String telephone;
    private Date uploadTime;
    private String uploader;
    private int sended;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public int getSended() {
        return sended;
    }

    public void setSended(int isSended) {
        this.sended = isSended;
    }
}
