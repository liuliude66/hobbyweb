package com.sms.forum.model;

import java.util.Date;

public class SMSContent {

    private long contentId;
    private Date uploadTime;
    private String uploader;
    private String content;

    public long getId() {
        return contentId;
    }

    public void setId(long contentId) {
        this.contentId = contentId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
