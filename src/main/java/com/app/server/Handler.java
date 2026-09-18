package com.app.server;

import com.app.controller.HelloController;
import com.app.exception.ValidationException;
import com.app.model.HelloRequest;
import com.app.response.ApiResponse;
import com.app.response.ResponseUtil;
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

        String method =
                exchange.getRequestMethod();

        String path =
                exchange.getRequestURI().getPath();

        Object response;

        // =========================
        // GET /hello
        // =========================
        if (path.equals("/hello")
                && method.equals("GET")) {

            response =
                    helloController.getHello();
        }

        // =========================
        // POST /hello
        // =========================
        else if (path.equals("/hello")
                && method.equals("POST")) {

            HelloRequest request =
                    readRequestBody(exchange);

            response =
                    helloController.postHello(request);
        }

        // =========================
        // PUT /hello
        // =========================
        else if (path.equals("/hello")
                && method.equals("PUT")) {

            response =
                    helloController.putHello();
        }

        // =========================
        // DELETE /hello
        // =========================
        else if (path.equals("/hello")
                && method.equals("DELETE")) {

            response =
                    helloController.deleteHello();
        }

        // =========================
        // ROUTE NOT FOUND
        // =========================
        else {

            sendError(
                    exchange,
                    404,
                    "Route not found"
            );

            return;
        }

        // Kirim response
        ResponseUtil.send(
                exchange,
                response
        );
    }

    // ==================================================
    // Membaca JSON Request Body
    // ==================================================
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

    // ==================================================
    // Error Response
    // ==================================================
    private void sendError(
            HttpExchange exchange,
            int statusCode,
            String message
    ) throws IOException {

        ApiResponse<Void> response =
                new ApiResponse<>(
                        false,
                        statusCode,
                        message,
                        null
                );

        ResponseUtil.send(
                exchange,
                response
        );
    }
}