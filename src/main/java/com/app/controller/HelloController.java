package com.app.controller;

import com.app.model.HelloResponse;
import com.app.service.HelloService;

public class HelloController {

    private final HelloService helloService;

    public HelloController() {

        this.helloService = new HelloService();
    }

    public HelloResponse getHello() {

        return helloService.getHello();
    }

    public HelloResponse postHello() {

        return helloService.postHello();
    }

    public HelloResponse putHello() {

        return helloService.putHello();
    }

    public HelloResponse deleteHello() {

        return helloService.deleteHello();
    }
}