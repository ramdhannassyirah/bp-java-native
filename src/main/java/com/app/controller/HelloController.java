package com.app.controller;

import com.app.model.HelloResponse;
import com.app.response.ApiResponse;
import com.app.service.HelloService;

public class HelloController {

    private final HelloService helloService;

    public HelloController() {

        this.helloService = new HelloService();
    }

    public ApiResponse<HelloResponse> getHello() {

        return helloService.getHello();
    }

    public ApiResponse<HelloResponse> postHello() {

        return helloService.postHello();
    }

    public ApiResponse<HelloResponse> putHello() {

        return helloService.putHello();
    }

    public ApiResponse<HelloResponse> deleteHello() {

        return helloService.deleteHello();
    }
}