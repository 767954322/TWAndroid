package com.tuwan.android.common.net.entity;


public class ApiException extends RuntimeException {


    public int code;

    public ApiException(String message) {
        super(message);
    }
}
