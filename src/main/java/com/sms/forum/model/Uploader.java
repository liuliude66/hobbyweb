package com.sms.forum.model;

import java.util.Date;

public class Uploader {

    private long uploaderId;
    private String username;
    private String password;
    private Date regTime;
    private Date lastLoginTime;
    private double balance;
    private double price;
    private int commitTotalCount;
    private int sendTotalCount;
    private int enabled;

    public long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCommitTotalCount() {
        return commitTotalCount;
    }

    public void setCommitTotalCount(int commitTotalCount) {
        this.commitTotalCount = commitTotalCount;
    }

    public int getSendTotalCount() {
        return sendTotalCount;
    }

    public void setSendTotalCount(int sendTotalCount) {
        this.sendTotalCount = sendTotalCount;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
