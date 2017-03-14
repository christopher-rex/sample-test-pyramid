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
//        TestDataHelper.cleanDb();
    }

    @Test
    public void testPing() throws Exception {
        GetMethod get = testServer.get("/ping", false);

        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(200, httpResponse.code());
        assertEquals("pong", new String(httpResponse.body()));
    }
}