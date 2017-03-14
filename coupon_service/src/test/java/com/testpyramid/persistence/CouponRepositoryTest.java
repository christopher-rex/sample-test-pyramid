package com.testpyramid.persistence;

import helpers.TestDataHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class CouponRepositoryTest {

    private final CouponRepository couponRepository = new CouponRepository();
    private final String couponId = "QWERTY";

    @Before
    public void setUp() throws Exception {
        TestDataHelper.createUnavailableCoupon(couponId, "some-user-id");
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
    public void findByIdReturnsCouponDetailsIfExists() throws Exception {
        Map<String, String> coupon = couponRepository.findById(couponId);

        assertNotNull(coupon);
        assertEquals(couponId, coupon.get("id"));
    }

    @Test
    public void findByIdDoesNotReturnUserId() throws Exception {
        Map<String, String> coupon = couponRepository.findById(couponId);

        assertNotNull(coupon);
        assertEquals(couponId, coupon.get("id"));
        assertFalse(coupon.containsKey("user_id"));
    }

    @Test
    public void findByIdReturnsAvailableIfNoUserHasUsedTheCoupon() throws Exception {
        String couponId = "available-coupon";
        TestDataHelper.createAvailableCoupon(couponId);
        Map<String, String> coupon = couponRepository.findById(couponId);

        assertNotNull(coupon);
        assertEquals(1, coupon.get("available"));
    }

    @Test
    public void findByIdReturnsUnavailableIfUserHasUsedTheCoupon() throws Exception {
        Map<String, String> coupon = couponRepository.findById(couponId);

        assertNotNull(coupon);
        assertEquals(0, coupon.get("available"));
    }
}