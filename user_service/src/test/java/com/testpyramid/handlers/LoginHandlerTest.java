package com.testpyramid.handlers;

import com.google.gson.Gson;
import com.testpyramid.HttpResult;
import com.testpyramid.persistence.UserRepository;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import spark.Request;

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
        when(mockUserRepository.findByEmailAndPassword(email, password))
                .thenReturn(null);

        LoginHandler handler = new LoginHandler(mockUserRepository);
        handler.handle(mockRequest);

        verify(mockUserRepository, times(1)).findByEmailAndPassword(email, password);
    }

    @Test
    public void returnsAllUserDetailsIfUserFound() throws Exception {
        String email = "email";
        String password = "password";
        String name = "your name";
        String active = "true";
        Request mockRequest = mockRequest(email, password);
        when(mockUserRepository.findByEmailAndPassword(email, password))
                .thenReturn(createResultMap(name, email, active));

        LoginHandler handler = new LoginHandler(mockUserRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest);

        assertTrue(result.isSuccess());
        assertEquals(name, result.getValue().get("name"));
        assertEquals(active, result.getValue().get("active"));
        assertEquals(email, result.getValue().get("email"));
    }

    @Test
    public void returnsUnauthorizedIfUserNotFound() throws Exception {
        when(mockUserRepository.findByEmailAndPassword(any(), any()))
                .thenReturn(null);

        LoginHandler handler = new LoginHandler(mockUserRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest("", ""));

        assertFalse(result.isSuccess());
        assertEquals(HttpStatus.UNAUTHORIZED_401, result.getStatusCode());
        assertEquals("", result.getErrorMessage());
    }

    @Test
    public void returnsUnauthorizedIfUserIsNotActive() throws Exception {
        String email = "email";
        String password = "password";

        when(mockUserRepository.findByEmailAndPassword(email, password))
                .thenReturn(createResultMap("some name", email, "false"));

        LoginHandler handler = new LoginHandler(mockUserRepository);
        HttpResult<Map<String, String>> result = handler.handle(mockRequest(email, password));

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

    private Map<String, String> createResultMap(String name, String email, String active) {
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("email", email);
        resultMap.put("active", active);
        return resultMap;
    }
}