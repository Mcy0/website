package com.mcy.website.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcy.website.entity.ResultDataEnum;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultData {
    private int code;
    private String msg;
    private Object data;

    public ResultData(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultData(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultData(ResultDataEnum resultDataEnum){
        this.code = resultDataEnum.getCode();
        this.msg = resultDataEnum.getMsg();
    }

    public ResultData(ResultDataEnum resultDataEnum, Object data){
        this.code = resultDataEnum.getCode();
        this.msg = resultDataEnum.getMsg();
        this.data = data;
    }

    public static void printWriterJSON(HttpServletResponse response, int status, int code,
                                         String msg, Object data) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        response.setStatus(status);
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        if (StringUtils.isNotBlank(msg)){
            map.put("msg", msg);
        }
        if (data != null){
            map.put("data", data);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        printWriter.write(objectMapper.writeValueAsString(map));
        printWriter.flush();
        printWriter.close();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
