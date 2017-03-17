package com.testpyramid.persistence;

import helpers.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

import static org.junit.Assert.*;

public class CouponRepositoryTest {

    private final CouponRepository couponRepository = new CouponRepository();
    private final String couponId = "QWERTY";

    @Before
    public void setUp() throws Exception {
        TestDataHelper.createCoupon(couponId, Timestamp.from(Instant.now()).toString(), 10, 10);
    }

    @After
    public void tearDown() throws Exception {
        TestDataHelper.cleanDb();
    }

    @Test
    public void findByIdReturnsNullIfCouponDoesNotExist() throws Exception {
        Map<String, String> coupon = couponRepository.findById("nonexistent-id");

        assertNull(coupon);
    }

    @Test
    public void findByIdReturnsAvailableForActiveAvailableCoupon() throws Exception {
        Map<String, String> coupon = couponRepository.findById(couponId);

        assertNotNull(coupon);
        assertEquals(couponId, coupon.get("id"));
    }

    @Test
    public void findByIdReturnsUnavailableForExpiredCoupon() throws Exception {
        String expiredCoupon = "EXP001";
        TestDataHelper.createCoupon(expiredCoupon, Timestamp.from(Instant.now().minusSeconds(86400)).toString(), 10, 10);
        Map<String, String> coupon = couponRepository.findById(expiredCoupon);

        assertNull(coupon);
    }

    @Test
    public void findByIdReturnsUnavailableForExhaustedCoupon() throws Exception {
        String exhaustedCoupon = "EXH001";
        TestDataHelper.createCoupon(exhaustedCoupon, Timestamp.from(Instant.now().plusSeconds(86400)).toString(), 10, 0);
        Map<String, String> coupon = couponRepository.findById(exhaustedCoupon);

        assertNull(coupon);
    }

}