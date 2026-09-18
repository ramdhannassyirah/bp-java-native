package com.app.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseUtil {

    private static final ObjectMapper objectMapper =
            new ObjectMapper();

    public static void send(
            HttpExchange exchange,
            Object response
    ) throws IOException {

        // Convert Java Object -> JSON
        String jsonResponse =
                objectMapper.writeValueAsString(response);

        // Default status
        int statusCode = 200;

        // Ambil status dari ApiResponse
        if (response instanceof ApiResponse<?> apiResponse) {

            statusCode =
                    apiResponse.getStatus();
        }

        // Convert JSON -> byte[]
        byte[] responseBytes =
                jsonResponse.getBytes(
                        StandardCharsets.UTF_8
                );

        // Set Content-Type
        exchange.getResponseHeaders()
                .set(
                        "Content-Type",
                        "application/json"
                );

        // Kirim HTTP status
        exchange.sendResponseHeaders(
                statusCode,
                responseBytes.length
        );

        // Kirim body
        exchange.getResponseBody()
                .write(responseBytes);

        // Tutup response
        exchange.getResponseBody()
                .close();
    }
}