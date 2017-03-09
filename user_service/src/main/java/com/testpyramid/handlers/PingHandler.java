package com.testpyramid.handlers;

import com.testpyramid.HttpResult;
import spark.Request;

public class PingHandler implements HandleableRoute<String> {
    @Override
    public HttpResult<String> handle(Request request) {
        return new HttpResult<>("pong");
    }
}
