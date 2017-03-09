package com.testpyramid.persistence;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;

import java.util.Map;

public class UserRepository {
    private ApplicationConfiguration config = Figaro.configure(null);
    private DBI dbi = new DBI("jdbc:sqlite:" +
            getClass().getClassLoader().getResource(
                    config.getValueAsString("DB_NAME") + ".db"));

    public Map<String, String> findByEmailAndPassword(String email, String password) {
        UserSql userSqlObject = dbi.open(UserSql.class);
        return userSqlObject.findByEmailAndPassword(email, password);
    }
}
