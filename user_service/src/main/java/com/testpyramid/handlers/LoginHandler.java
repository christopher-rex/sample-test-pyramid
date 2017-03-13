package com.testpyramid.handlers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.testpyramid.HttpResult;
import com.testpyramid.persistence.UserRepository;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;

import java.util.Map;

public class LoginHandler implements HandleableRoute<Map<String, String>> {
    private final UserRepository userRepository;

    public LoginHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HttpResult<Map<String, String>> handle(Request request) {
        Gson gson = new Gson();
        Map<String, String> body = gson.fromJson(request.body(), new TypeToken<Map<String, String>>() {}.getType());
        Map<String, String> result = userRepository.findByEmailAndPassword(body.get("email"), body.get("password"));

        if (result == null) {
            return new HttpResult<>(HttpStatus.UNAUTHORIZED_401, "");
        }

        if (result.get("active").equals("false")) {
            return new HttpResult<>(HttpStatus.UNAUTHORIZED_401, "");
        }

        return new HttpResult<>(result);
    }
}
