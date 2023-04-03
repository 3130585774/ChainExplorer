package com.hbgcjsxy.chainexplorer;

import java.util.List;

public class ResponseTransaction {

    private String code;

    private String msg;

    private List<Data_Transaction> data;

    public List<Data_Transaction> getData() {
        return data;
    }

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

    public void setData(List<Data_Transaction> data) {
        this.data = data;
    }
}
