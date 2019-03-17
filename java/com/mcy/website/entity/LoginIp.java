package com.mcy.website.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginIp implements Serializable {
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private static final long serialVersionUID = 3332259677462191355L;
    private int id;
    private int uid;
    private String ip;
    private Date loginDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
