package com.testpyramid.persistence;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Map;

@RegisterMapper(DefaultMapper.class)
public interface CouponSql {
    @SqlQuery("SELECT id, user_id IS NULL AS available " +
            "FROM coupons " +
            "WHERE id = :id")
    Map<String, String> findById(@Bind("id") String id);
}
