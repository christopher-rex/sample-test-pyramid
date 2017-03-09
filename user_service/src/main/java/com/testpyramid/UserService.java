package com.testpyramid;

import com.testpyramid.handlers.LoginHandler;
import com.testpyramid.handlers.PingHandler;
import com.testpyramid.persistence.UserRepository;

import static spark.Spark.*;

public class UserService {
    public static void main(String[] args) {
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/ping", new PingHandler());
        post("/login", new LoginHandler(new UserRepository()));
    }
}