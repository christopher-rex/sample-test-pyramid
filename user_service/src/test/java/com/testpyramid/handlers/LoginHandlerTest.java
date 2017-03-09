package com.testpyramid.handlers;

import com.google.gson.Gson;
import com.testpyramid.HttpResult;
import com.testpyramid.persistence.UserRepository;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import spark.Request;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoginHandlerTest {
    private final Gson gson = new Gson();
    private UserRepository mockUserRepository = mock(UserRepository.class);

    @Test
    public void getsParametersFromRequestBody() throws Exception {
        String email = "mail@example.com";
        String password = "password1";
        Request mockRequest = mockRequest(email, password);

        LoginHandler handler = new LoginHandler(mockUserRepository);
        handler.handle(mockRequest);

        verify(mockUserRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    public void returnsUserDetailsIfUserFound() throws Exception {
        String email = "email";
        String password = "password";
        Request mockRequest = mockRequest(email, password);
        when(mockUserRepository.findByEmailAndPassword(email, password))
                .thenReturn(Collections.singletonMap("name", "your name"));

        LoginHandler handler = new LoginHandler(mockUserRepository);
        HttpResult<String> result = handler.handle(mockRequest);

        assertTrue(result.isSuccess());
        assertEquals("your name", result.getValue());
    }

    @Test
    public void returnsUnauthorizedIfUserNotFound() throws Exception {
        when(mockUserRepository.findByEmailAndPassword(any(), any()))
                .thenReturn(null);

        LoginHandler handler = new LoginHandler(mockUserRepository);
        HttpResult<String> result = handler.handle(mockRequest("", ""));

        assertFalse(result.isSuccess());
        assertEquals(HttpStatus.UNAUTHORIZED_401, result.getStatusCode());
        assertEquals("", result.getErrorMessage());
    }

    private Request mockRequest(String email, String password) {
        Request mockRequest = mock(Request.class);
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("email", email);
        requestMap.put("password", password);
        String requestJson = gson.toJson(requestMap);

        when(mockRequest.body()).thenReturn(requestJson);

        return mockRequest;
    }
}