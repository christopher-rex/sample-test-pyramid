package com.testpyramid.persistence;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;

import java.util.Map;

public class UserRepository {
    public Map<String, String> findByEmailAndPassword(String email, String password) {
        ApplicationConfiguration config = Figaro.configure(null);
        DBI dbi = new DBI("jdbc:sqlite:" +
                getClass().getClassLoader().getResource(
                        config.getValueAsString("DB_NAME") + ".db"));
        UserDao dao = dbi.open(UserDao.class);

        return dao.findByEmailAndPassword(email, password);
    }
}
