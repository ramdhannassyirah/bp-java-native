package com.app.server;

import com.app.controller.HelloController;
import com.app.exception.ValidationException;
import com.app.model.HelloRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
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

        try {

            handleRequest(exchange);

        } catch (ValidationException e) {

            sendError(
                    exchange,
                    400,
                    e.getMessage()
            );

        } catch (Exception e) {

            e.printStackTrace();

            sendError(
                    exchange,
                    500,
                    "Internal server error"
            );
        }
    }

    private void handleRequest(
            HttpExchange exchange
    ) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        Object response;

        if (path.equals("/hello") && method.equals("GET")) {

            response = helloController.getHello();

        } else if (path.equals("/hello") && method.equals("POST")) {

            HelloRequest request =
                    readRequestBody(exchange);

            response = helloController.postHello(request);

        } else if (path.equals("/hello") && method.equals("PUT")) {

            response = helloController.putHello();

        } else if (path.equals("/hello") && method.equals("DELETE")) {

            response = helloController.deleteHello();

        } else {

            sendError(
                    exchange,
                    404,
                    "Route not found"
            );

            return;
        }

        String jsonResponse =
                objectMapper.writeValueAsString(response);

        sendResponse(
                exchange,
                200,
                jsonResponse
        );
    }

    private HelloRequest readRequestBody(
            HttpExchange exchange
    ) throws IOException {

        InputStream inputStream =
                exchange.getRequestBody();

        String requestBody =
                new String(
                        inputStream.readAllBytes(),
                        StandardCharsets.UTF_8
                );

        return objectMapper.readValue(
                requestBody,
                HelloRequest.class
        );
    }

    private void sendError(
            HttpExchange exchange,
            int statusCode,
            String message
    ) throws IOException {

        String response = """
                {
                    "success": false,
                    "message": "%s",
                    "data": null
                }
                """.formatted(message);

        sendResponse(
                exchange,
                statusCode,
                response
        );
    }

    private void sendResponse(
            HttpExchange exchange,
            int statusCode,
            String response
    ) throws IOException {

        byte[] responseBytes =
                response.getBytes(StandardCharsets.UTF_8);

        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "application/json"
                );

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