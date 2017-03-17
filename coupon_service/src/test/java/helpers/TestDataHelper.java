package helpers;

import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import org.skife.jdbi.v2.DBI;

public class TestDataHelper {
    private static ApplicationConfiguration config = Figaro.configure(null);
    private static DBI dbi = new DBI("jdbc:sqlite:" +
            TestDataHelper.class.getClassLoader().getResource(
                    config.getValueAsString("DB_NAME") + ".db"));

    public static void createCoupon(String id, String valid_until, int max_redemptions, int available_redemptions) {
        dbi.withHandle(handle -> {
            handle.execute("INSERT INTO coupons (id,valid_until,max_redemptions,available_redemptions) VALUES ('"
                    + id + "','"
                    + valid_until + "',"
                    + max_redemptions + ","
                    + available_redemptions + ")");
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
