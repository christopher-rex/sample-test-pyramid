import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.sparkjava.test.SparkServer;
import com.testpyramid.UserService;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceTest {
    public static class UserServiceTestSparkApplication implements SparkApplication {
        @Override
        public void init() {
            UserService.main(new String[]{});
        }
    }

    @ClassRule
    public static SparkServer<UserServiceTestSparkApplication> testServer =
            new SparkServer<>(UserServiceTestSparkApplication.class, 4568);

    @Test
    public void testPing() throws Exception {
        GetMethod get = testServer.get("/ping", false);

        HttpResponse httpResponse = testServer.execute(get);

        assertEquals(200, httpResponse.code());
        assertEquals("pong", new String(httpResponse.body()));
        assertNotNull(testServer.getApplication());
    }
}