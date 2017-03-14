package com.testpyramid;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.google.gson.Gson;
import com.testpyramid.handlers.CouponHandler;
import com.testpyramid.handlers.PingHandler;
import com.testpyramid.persistence.CouponRepository;

import static spark.Spark.*;

public class CouponService {
    private static final Gson gson = new Gson();
    private static final ApplicationConfiguration config = Figaro.configure(null);

    public static void main(String[] args) {
        port(config.getValueAsInt("PORT"));

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/ping", new PingHandler());
        get("/coupons/:id", new CouponHandler(new CouponRepository()), gson::toJson);
    }
}