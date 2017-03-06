package com.testpyramid.persistence;

import org.skife.jdbi.v2.DBI;

public class UserRepository {
    public String findNameById(String id) {
        DBI dbi = new DBI("jdbc:sqlite:user_service.db");
        UserDao dao = dbi.open(UserDao.class);

        return dao.findNameById(id);
    }
}
