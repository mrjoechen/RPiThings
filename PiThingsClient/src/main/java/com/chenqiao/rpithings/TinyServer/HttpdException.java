package com.chenqiao.rpithings.TinyServer;

public class HttpdException extends Exception {
    final int errCode;
    final String errMsg;

    public HttpdException(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}