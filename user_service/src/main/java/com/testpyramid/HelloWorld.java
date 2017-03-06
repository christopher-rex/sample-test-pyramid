package com.testpyramid;

import static spark.Spark.*;

public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
        get("/ping", (req, res) -> "Pong");
    }
}
