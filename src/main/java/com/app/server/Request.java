package com.app.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Request {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final HttpExchange exchange;
    private final Map<String, String> params;

    public Request(
            HttpExchange exchange,
            Map<String, String> params) {
        this.exchange = exchange;
        this.params = params;
    }

    // =========================
    // HTTP
    // =========================

    public String method() {

        return exchange.getRequestMethod();
    }

    public String path() {

        return exchange.getRequestURI().getPath();
    }

    // =========================
    // PATH PARAMETER
    // =========================

    public String param(String name) {

        return params.get(name);
    }

    public Long getId() {

        String id = param("id");

        if (id == null) {
            return null;
        }

        return Long.parseLong(id);
    }

    // =========================
    // QUERY PARAMETER
    // =========================

    public String query(String name) {

        String query = exchange.getRequestURI().getRawQuery();

        if (query == null) {
            return null;
        }

        for (String parameter : query.split("&")) {

            String[] parts = parameter.split("=", 2);

            String key = URLDecoder.decode(parts[0], StandardCharsets.UTF_8);

            if (key.equals(name)) {

                if (parts.length == 1) {
                    return "";
                }

                return URLDecoder.decode(parts[1], StandardCharsets.UTF_8);
            }
        }

        return null;
    }

    // =========================
    // JSON BODY
    // =========================

    public <T> T body(
            Class<T> type) throws IOException {

        return objectMapper.readValue(exchange.getRequestBody(), type);
    }
}