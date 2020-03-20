package com.dksoft.formshiftserver.Model;

import javax.persistence.Entity;

public class HttpResponse {

    public HttpResponse() {
    }

    public HttpResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
