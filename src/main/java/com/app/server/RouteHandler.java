package com.app.server;

import com.app.response.ApiResponse;

@FunctionalInterface
public interface RouteHandler {

    ApiResponse<?> handle(Request request) throws Exception;
}