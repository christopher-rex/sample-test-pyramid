package com.testpyramid;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.testpyramid.handlers.PingHandler;

import static spark.Spark.*;

public class CouponService {
    private static final ApplicationConfiguration config = Figaro.configure(null);

    public static void main(String[] args) {
        port(config.getValueAsInt("PORT"));

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/ping", new PingHandler());
    }
}