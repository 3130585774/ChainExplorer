package com.hbgcjsxy.chainexplorer;

import java.util.List;

public class ResponseInfo {
    private String code;

    private String msg;

    private List<Data_info> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data_info> getData() {
        return data;
    }

    public void setData(List<Data_info> data) {
        this.data = data;
    }
}
