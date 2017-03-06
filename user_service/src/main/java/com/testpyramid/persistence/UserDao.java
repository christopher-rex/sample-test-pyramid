package com.testpyramid.persistence;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface UserDao {
    @SqlQuery("SELECT name FROM users LIMIT 1")
    String findNameById(@Bind("id") String id);
}
