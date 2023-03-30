package com.hbgcjsxy.chainexplorer;

import java.io.Serializable;

public class ResponseSerializable implements Serializable {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
