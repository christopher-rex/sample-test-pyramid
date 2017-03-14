package com.testpyramid.handlers;

import com.testpyramid.HttpResult;
import com.testpyramid.persistence.CouponRepository;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;

import java.util.Map;

public class CouponHandler implements HandleableRoute<Map<String, String>> {
    private final CouponRepository couponRepository;

    public CouponHandler(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public HttpResult<Map<String, String>> handle(Request request) {
        String couponId = request.params("id");

        if (couponId == null || couponId.isEmpty()) {
            return new HttpResult<>(HttpStatus.BAD_REQUEST_400, "");
        }

        Map<String, String> result = couponRepository.findById(request.params("id"));

        if (result == null) {
            return new HttpResult<>(HttpStatus.NOT_FOUND_404, "");
        }

        return new HttpResult<>(result);
    }
}
