package com.mcy.website.entity;

import java.io.Serializable;

//角色信息
public class Role implements Serializable {

    private static final long serialVersionUID = -2870063971373257567L;

    private Integer id;
    private String name;
    private String nameZh;//角色描述

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }
}
