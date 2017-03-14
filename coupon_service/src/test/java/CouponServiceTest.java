import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import com.gojek.ApplicationConfiguration;
import com.gojek.Figaro;
import com.testpyramid.CouponService;
import helpers.TestDataHelper;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CouponServiceTest {
    private static final ApplicationConfiguration config = Figaro.configure(null);

    public static class CouponServiceTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            CouponService.main(new String[]{});
        }
    }

    @ClassRule
    public static SparkServer<CouponServiceTestSparkApplication> testServer =
            new SparkServer<>(CouponServiceTestSparkApplication.class, config.getValueAsInt("PORT"));

    @Before
    public void setUp(){
        TestDataHelper.cleanDb();
    }

    @Test
    public void testPing() throws Exception {
        GetMethod get = testServer.get("/ping", false);

        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(200, httpResponse.code());
        assertEquals("pong", new String(httpResponse.body()));
    }

    @Test
    public void couponReturns404IfNotFound() throws Exception {
        GetMethod get = testServer.get("/coupons/QWERTY", false);

        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(404, httpResponse.code());
        assertEquals("", new String(httpResponse.body()));
    }

    @Test
    public void couponReturnsCouponDetailsIfFound() throws Exception {
        String couponId = "ABCXYZ";
        TestDataHelper.createUnavailableCoupon(couponId, "some-user-id");
        GetMethod get = testServer.get("/coupons/" + couponId, false);

        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(200, httpResponse.code());
        assertFalse(new String(httpResponse.body()).isEmpty());
    }
}