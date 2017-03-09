package helpers;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.util.UUID;

public class TestDataHelper {
    private static ApplicationConfiguration config = Figaro.configure(null);
    private static DBI dbi = new DBI("jdbc:sqlite:" +
            TestDataHelper.class.getClassLoader().getResource(
                    config.getValueAsString("DB_NAME") + ".db"));

    public static void createUser() {
        Handle h = dbi.open();
        h.execute("INSERT INTO users VALUES ('" + UUID.randomUUID() + "', 'Test User', 'test@test.com', 'password')");

    }

    public static void cleanDb() {
        Handle h = dbi.open();
        h.execute("DELETE FROM users");
    }
}
