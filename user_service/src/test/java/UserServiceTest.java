import com.despegar.http.client.GetMethod;
import com.despegar.http.client.HttpResponse;
import com.despegar.http.client.PostMethod;
import com.despegar.sparkjava.test.SparkServer;
import com.testpyramid.UserService;
import helpers.TestDataHelper;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import spark.servlet.SparkApplication;

import static org.junit.Assert.*;

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
    public void testLoginSucceedsForValidUser() throws Exception {
        TestDataHelper.createUser("Tom", "tom@test.com", "pwd", "true");

        PostMethod post = testServer.post("/login", "{\"email\":\"tom@test.com\",\"password\":\"pwd\"}", true);
        HttpResponse httpResponse = testServer.execute(post);
        assertEquals(200, httpResponse.code());
        assertEquals("Tom", new String(httpResponse.body()));
    }

    @Test
    public void testLoginFailsIfUserNotFound() throws Exception {
        PostMethod post = testServer.post("/login", "{\"email\":\"user\",\"password\":\"abcd\"}", true);
        HttpResponse httpResponse = testServer.execute(post);
        assertEquals(401, httpResponse.code());
        assertEquals("Unauthorized", httpResponse.message());
        assertTrue((httpResponse.body()).length == 0);
    }

    @Test
    public void testLoginFailsIfUserNotActive() throws Exception {
        TestDataHelper.createUser("Tom", "tom@test.com", "pwd", "false");

        PostMethod post = testServer.post("/login", "{\"email\":\"tom@test.com\",\"password\":\"pwd\"}", true);
        HttpResponse httpResponse = testServer.execute(post);
        assertEquals(401, httpResponse.code());
        assertEquals("Unauthorized", httpResponse.message());
        assertTrue((httpResponse.body()).length == 0);
    }

}