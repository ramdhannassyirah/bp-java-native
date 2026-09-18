package com.app.server;

import com.app.controller.UserController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApp {

    private HttpServer server;

    public void start() {

        try {

            Router router =
                    new Router();

            UserController userController =
                    new UserController();

            // USER ROUTES

            router.get(
                    "/users",
                    userController::findAll
            );

            router.get(
                    "/users/{id}",
                    userController::findById
            );

            router.post(
                    "/users",
                    userController::create
            );

            router.put(
                    "/users/{id}",
                    userController::update
            );

            router.delete(
                    "/users/{id}",
                    userController::delete
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