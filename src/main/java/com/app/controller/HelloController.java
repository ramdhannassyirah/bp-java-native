package com.app.controller;

import com.app.model.HelloRequest;
import com.app.model.HelloResponse;
import com.app.response.ApiResponse;
import com.app.server.Request;
import com.app.service.HelloService;
import com.app.validation.HelloValidator;

public class HelloController {

    private final HelloService helloService;
    private final HelloValidator helloValidator;

    public HelloController() {

        this.helloService =
                new HelloService();

        this.helloValidator =
                new HelloValidator();
    }

    public ApiResponse<HelloResponse> getHello(
            Request request
    ) {

        return helloService.getHello();
    }

    public ApiResponse<HelloResponse> postHello(
            Request request
    ) throws Exception {

        HelloRequest body =
                request.body(
                        HelloRequest.class
                );

        helloValidator.validate(body);

        return helloService.postHello(body);
    }
}