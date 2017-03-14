package com.testpyramid.handlers;

import com.testpyramid.HttpResult;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.halt;

public interface HandleableRoute<T> extends Route {
    HttpResult<T> handle(Request request);

    @Override
    default Object handle(Request request, Response response) {
        HttpResult<T> result = handle(request);
        if (!result.isSuccess()) {
            halt(result.getStatusCode(), result.getErrorMessage());
        }
        return result.getValue();
    }
}
