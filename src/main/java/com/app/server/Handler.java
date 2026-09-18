package com.app.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        String response;

        if (path.equals("/") && method.equals("GET")) {

            response = """
                    {
                        "message": "Java Native API is running"
                    }
                    """;

        } else if (path.equals("/hello") && method.equals("GET")) {

            response = """
                    {
                        "message": "Hello from GET"
                    }
                    """;

        } else if (path.equals("/hello") && method.equals("POST")) {

            response = """
                    {
                        "message": "Hello from POST"
                    }
                    """;

        } else if (path.equals("/hello") && method.equals("PUT")) {

            response = """
                    {
                        "message": "Hello from PUT"
                    }
                    """;

        } else if (path.equals("/hello") && method.equals("DELETE")) {

            response = """
                    {
                        "message": "Hello from DELETE"
                    }
                    """;

        } else {

            response = """
                    {
                        "message": "Route not found"
                    }
                    """;

            sendResponse(exchange, 404, response);
            return;
        }

        sendResponse(exchange, 200, response);
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