package com.app.server;

import com.app.exception.ErrorHandler;
import com.app.response.ApiResponse;
import com.app.response.ResponseUtil;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class Handler implements HttpHandler {

    private final Router router;

    public Handler(Router router) {
        this.router = router;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {

            ApiResponse<?> response = router.handle(exchange);

            ResponseUtil.send(exchange, response);

        } catch (Exception e) {

            ErrorHandler.handle(exchange, e);
        }
    }
}