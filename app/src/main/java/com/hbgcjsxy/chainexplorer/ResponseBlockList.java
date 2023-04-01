package com.hbgcjsxy.chainexplorer;

import java.util.ArrayList;
import java.util.List;

public class ResponseBlockList {
    private String code;

    private String msg;

    private List<Data_BlockList> data;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(List<Data_BlockList> data) {
        this.data = data;
    }

    public List<Data_BlockList> getData() {
        return this.data;
    }
}
