package com.app.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApp {

    private HttpServer server;

    public void start() {

        try {

            server = HttpServer.create(
                    new InetSocketAddress(8080),
                    0
            );

            Router router = new Router(server);

            router.registerRoutes();

            server.start();

            System.out.println(
                    "Server running on http://localhost:8080"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}