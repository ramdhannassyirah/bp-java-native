package com.app.controller;

public class HelloController {

    public String getHello() {

        return """
                {
                    "message": "Hello from Controller"
                }
                """;
    }

    public String postHello() {

        return """
                {
                    "message": "POST Hello from Controller"
                }
                """;
    }

    public String putHello() {

        return """
                {
                    "message": "PUT Hello from Controller"
                }
                """;
    }

    public String deleteHello() {

        return """
                {
                    "message": "DELETE Hello from Controller"
                }
                """;
    }
}