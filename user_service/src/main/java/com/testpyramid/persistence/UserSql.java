package com.testpyramid.persistence;

import org.skife.jdbi.v2.DefaultMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.Map;

@RegisterMapper(DefaultMapper.class)
public interface UserSql {
    @SqlQuery("SELECT id, name, email, active, auth_token " +
            "FROM users " +
            "WHERE email = :email AND password = :password")
    Map<String, String> findByEmailAndPassword(@Bind("email") String email,
                                               @Bind("password") String password);
}
