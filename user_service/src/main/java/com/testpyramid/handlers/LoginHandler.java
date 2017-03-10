package com.testpyramid.handlers;

import com.google.gson.Gson;
import com.testpyramid.HttpResult;
import com.testpyramid.persistence.UserRepository;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;

import java.util.Map;

public class LoginHandler implements HandleableRoute<String> {
    private final UserRepository userRepository;

    public LoginHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HttpResult<String> handle(Request request) {
        Gson gson = new Gson();
        Map<String, String> body = gson.fromJson(request.body(), Map.class);
        Map<String, String> result = userRepository.findByEmailAndPassword(body.get("email"), body.get("password"));

        if (result == null) {
            return new HttpResult<>(HttpStatus.UNAUTHORIZED_401, "");
        }

        if (result.get("active").equals("false")) {
            return new HttpResult<>(HttpStatus.UNAUTHORIZED_401, "");
        }

        return new HttpResult<>(result.get("name"));
    }
}
