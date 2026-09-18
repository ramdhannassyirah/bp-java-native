package com.app.server;

import com.app.controller.HelloController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Handler implements HttpHandler {

    private final HelloController helloController;
    private final ObjectMapper objectMapper;

    public Handler() {

        this.helloController = new HelloController();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        Object response;

        if (path.equals("/hello") && method.equals("GET")) {

            response = helloController.getHello();

        } else if (path.equals("/hello") && method.equals("POST")) {

            response = helloController.postHello();

        } else if (path.equals("/hello") && method.equals("PUT")) {

            response = helloController.putHello();

        } else if (path.equals("/hello") && method.equals("DELETE")) {

            response = helloController.deleteHello();

        } else {

            response = """
                    {
                        "message": "Route not found"
                    }
                    """;

            sendResponse(exchange, 404, response.toString());

            return;
        }

        String jsonResponse =
                objectMapper.writeValueAsString(response);

        sendResponse(exchange, 200, jsonResponse);
    }

    private void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        byte[] responseBytes =
                response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .set("Content-Type", "application/json");

        exchange.sendResponseHeaders(
                statusCode,
                responseBytes.length
        );

        exchange.getResponseBody()
                .write(responseBytes);

        exchange.getResponseBody()
                .close();
    }
}