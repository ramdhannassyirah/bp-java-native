package com.app.service;

import com.app.model.HelloResponse;

public class HelloService {

    public HelloResponse getHello() {

        return new HelloResponse(
                "Hello from Service"
        );
    }

    public HelloResponse postHello() {

        return new HelloResponse(
                "POST Hello from Service"
        );
    }

    public HelloResponse putHello() {

        return new HelloResponse(
                "PUT Hello from Service"
        );
    }

    public HelloResponse deleteHello() {

        return new HelloResponse(
                "DELETE Hello from Service"
        );
    }
}