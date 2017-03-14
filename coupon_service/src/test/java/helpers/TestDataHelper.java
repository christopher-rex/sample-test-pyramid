package helpers;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;

public class TestDataHelper {
    private static ApplicationConfiguration config = Figaro.configure(null);
    private static DBI dbi = new DBI("jdbc:sqlite:" +
            TestDataHelper.class.getClassLoader().getResource(
                    config.getValueAsString("DB_NAME") + ".db"));

    public static void createAvailableCoupon(String id) {
        dbi.withHandle(handle -> {
            handle.execute("INSERT INTO coupons (id) VALUES ('" + id + "')");
            return null;
        });
    }

    public static void createUnavailableCoupon(String id, String userId) {
        dbi.withHandle(handle -> {
            handle.execute("INSERT INTO coupons (id, user_id) VALUES ('"
                    + id + "', '"
                    + userId + "')");
            return null;
        });
    }

    public static void cleanDb() {
        dbi.withHandle(h -> {
            h.execute("DELETE FROM coupons");
            return null;
        });
    }
}
