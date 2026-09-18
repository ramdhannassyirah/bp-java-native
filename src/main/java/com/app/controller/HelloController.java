package com.app.controller;

import com.app.exception.ValidationException;
import com.app.model.HelloRequest;
import com.app.model.HelloResponse;
import com.app.response.ApiResponse;
import com.app.service.HelloService;
import com.app.validation.HelloValidator;

public class HelloController {

    private final HelloService helloService;
    private final HelloValidator helloValidator;

    public HelloController() {

        this.helloService = new HelloService();
        this.helloValidator = new HelloValidator();
    }

    public ApiResponse<HelloResponse> getHello() {

        return helloService.getHello();
    }

    public ApiResponse<HelloResponse> postHello(
            HelloRequest request
    ) {

        helloValidator.validate(request);

        return helloService.postHello(request);
    }

    public ApiResponse<HelloResponse> putHello() {

        return helloService.putHello();
    }

    public ApiResponse<HelloResponse> deleteHello() {

        return helloService.deleteHello();
    }
}