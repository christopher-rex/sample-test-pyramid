package com.testpyramid.handlers;

import com.testpyramid.HttpResult;
import org.junit.Test;
import spark.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PingHandlerTest {
    @Test
    public void returnsPong() throws Exception {
        PingHandler handler = new PingHandler();
        HttpResult<String> result = handler.handle(mock(Request.class));

        assertTrue(result.isSuccess());
        assertEquals("pong", result.getValue());
    }
}