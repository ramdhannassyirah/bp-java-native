package com.app.server;

import com.app.controller.HelloController;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApp {

    private HttpServer server;

    public void start() {

        try {

            Router router = new Router();

            HelloController helloController =
                    new HelloController();


            // HELLO
            router.get(
                    "/hello",
                    helloController::getHello
            );

            router.post(
                    "/hello",
                    helloController::postHello
            );


            // SERVER
            server = HttpServer.create(
                    new InetSocketAddress(8080),
                    0
            );

            server.createContext(
                    "/",
                    new Handler(router)
            );

            server.start();

            System.out.println(
                    "Server running on http://localhost:8080"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}