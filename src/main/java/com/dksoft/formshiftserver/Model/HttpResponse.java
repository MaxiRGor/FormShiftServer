package com.dksoft.formshiftserver.Model;

public class HttpResponse {

    public HttpResponse() {
    }

    public HttpResponse(int code, String description) {
        this.code = code;
        this.description = description;
    }

    private int code;
    private String description;

}
