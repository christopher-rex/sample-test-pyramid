package helpers;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;

import java.util.UUID;

public class TestDataHelper {
    private static ApplicationConfiguration config = Figaro.configure(null);
    private static DBI dbi = new DBI("jdbc:sqlite:" +
            TestDataHelper.class.getClassLoader().getResource(
                    config.getValueAsString("DB_NAME") + ".db"));

    public static void createUser() {
        createUser("Test User", "test@test.com", "password", "true");
    }

    public static void createUser(String name, String email, String password, String isActive) {
        dbi.withHandle(handle -> {
            handle.execute("INSERT INTO users (id, auth_token, name, email, password, active) VALUES ('"
                    + UUID.randomUUID() + "', '"
                    + UUID.randomUUID() + "', '"
                    + name + "', '"
                    + email + "', '"
                    + password + "', '"
                    + isActive + "')");
            return null;
        });
    }

    public static void cleanDb() {
        dbi.withHandle(h -> {
            h.execute("DELETE FROM users");
            return null;
        });
    }
}
