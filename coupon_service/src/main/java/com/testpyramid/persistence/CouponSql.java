package com.testpyramid.persistence;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Map;

@RegisterMapper(DefaultMapper.class)
public interface CouponSql {
    @SqlQuery("SELECT id " +
            "FROM coupons " +
            "WHERE id = :id " +
            "AND valid_until >= date('now') " +
            "AND available_redemptions > 0")
    Map<String, String> findById(@Bind("id") String id);
}
