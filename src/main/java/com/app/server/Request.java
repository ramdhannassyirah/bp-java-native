package com.app.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Map;

public class Request {

    private static final ObjectMapper objectMapper =
            new ObjectMapper();

    private final HttpExchange exchange;
    private final Map<String, String> params;

    public Request(
            HttpExchange exchange,
            Map<String, String> params
    ) {
        this.exchange = exchange;
        this.params = params;
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    public String getMethod() {
        return exchange.getRequestMethod();
    }

    public String getPath() {
        return exchange.getRequestURI()
                .getPath();
    }

    public String getParam(String name) {
        return params.get(name);
    }

    public Long getId() {

        String id = params.get("id");

        if (id == null) {
            return null;
        }

        return Long.parseLong(id);
    }

    public <T> T body(Class<T> type)
            throws IOException {

        return objectMapper.readValue(
                exchange.getRequestBody(),
                type
        );
    }
}