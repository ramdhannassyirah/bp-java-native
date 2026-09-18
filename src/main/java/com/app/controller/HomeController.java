package com.app.controller;

import com.app.response.ApiResponse;
import com.app.server.Request;

import java.util.Map;

public class HomeController {

    public ApiResponse<Map<String, Object>> index(Request request) {

        Map<String, Object> data = Map.of(
                "name", "Java Native API",
                "version", "1.0.0",
                "status", "running");

        return new ApiResponse<>(true, 200, "API berhasil dijalankan", data);
    }
}