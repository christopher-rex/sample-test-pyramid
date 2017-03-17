package com.testpyramid.handlers;

import com.testpyramid.HttpResult;
import com.testpyramid.persistence.CouponRepository;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import spark.Request;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CouponHandlerTest {
    private CouponRepository mockCouponRepository = mock(CouponRepository.class);
    private final Request mockRequest = mock(Request.class);

    @Test
    public void returnsAllCouponDetailsIfFound() throws Exception {
        String couponId = "coupon-id";
        when(mockRequest.params("id")).thenReturn(couponId);
        when(mockCouponRepository.findById(couponId))
                .thenReturn(new HashMap<String, String>() {{
                    put("id", couponId);
                }});

        CouponHandler handler = new CouponHandler(mockCouponRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest);

        assertTrue(result.isSuccess());
        assertEquals(couponId, result.getValue().get("id"));
    }

    @Test
    public void returnsBadRequestIfNoIdProvided() throws Exception {
        CouponHandler handler = new CouponHandler(mockCouponRepository);
        HttpResult<Map<String, String>> result = handler.handle(mock(Request.class));

        assertFalse(result.isSuccess());
        assertEquals(HttpStatus.BAD_REQUEST_400, result.getStatusCode());
    }

    @Test
    public void returnsNotFoundIfNotFound() throws Exception {
        String couponId = "coupon-id";
        when(mockRequest.params("id")).thenReturn(couponId);
        when(mockCouponRepository.findById(couponId))
                .thenReturn(null);

        CouponHandler handler = new CouponHandler(mockCouponRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest);

        assertFalse(result.isSuccess());
        assertEquals(HttpStatus.NOT_FOUND_404, result.getStatusCode());
    }

    @Test
    public void returnsNotFoundIfCouponUnavailableForRedemption() throws Exception {
        String couponId = "coupon-id";
        when(mockRequest.params("id")).thenReturn(couponId);
        when(mockCouponRepository.findById(couponId))
                .thenReturn(null);

        CouponHandler handler = new CouponHandler(mockCouponRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest);

        assertFalse(result.isSuccess());
        assertEquals(HttpStatus.NOT_FOUND_404, result.getStatusCode());
    }
}