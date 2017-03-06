package com.testpyramid.persistence;

import org.skife.jdbi.v2.DBI;

import java.util.Map;

public class UserRepository {
    public Map<String, String> findByEmailAndPassword(String email, String password) {
        DBI dbi = new DBI("jdbc:sqlite:user_service.db");
        UserDao dao = dbi.open(UserDao.class);

        return dao.findByEmailAndPassword(email, password);
    }
}
