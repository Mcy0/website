package com.mcy.website.entity;

public enum ResultDataEnum {

    SUCCESS_200(2000, "成功"),
    FAILD_500(5000, "未知错误!"),
    FAILD_401(4001, "登录失败!"),
    FAILD_1001(1001,"数据不合法"),
    FAILD_3003(3003,"用户已存在");

    private int code;
    private String msg;
    ResultDataEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    ResultDataEnum(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
