package com.testpyramid;

import com.testpyramid.persistence.UserRepository;

import static spark.Spark.get;

public class HelloWorld {
    public static void main(String[] args) {
        get("/ping", (req, res) -> "pong");
        get("/user", (req, res) -> new UserRepository().findNameById(""));
    }
}