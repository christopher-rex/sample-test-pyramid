package com.testpyramid;

import com.google.gson.Gson;
import com.testpyramid.persistence.UserRepository;
import spark.Request;
import spark.Response;

import java.util.Map;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/ping", (req, res) -> "pong");

        post("/login", (Request req, Response res) -> {
            Gson gson = new Gson();
            Map<String, String> body = gson.fromJson(req.body(), Map.class);

            Map<String, String> result = new UserRepository()
                    .findByEmailAndPassword(body.get("email"), body.get("password"));

            if (result == null) {
                halt(401);
            }

            return "{}";
        });
    }
}