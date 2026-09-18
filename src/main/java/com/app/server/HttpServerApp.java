package com.app.server;

// CONTROLLER
import com.app.controller.UserController;
import com.app.controller.HomeController;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpServerApp {

        private HttpServer server;

        public void start() {

                try {

                        Router router = new Router();

                        UserController userController = new UserController();
                        HomeController homeController = new HomeController();

                        // ROUTES
                        router.get("/", homeController::index);

                        // USERS
                        router.get("api//users", userController::findAll);
                        router.get("api//users/{id}", userController::findById);
                        router.post("api//users", userController::create);
                        router.put("api//users/{id}", userController::update);
                        router.delete("api//users/{id}", userController::delete);

                        // SERVER
                        server = HttpServer.create(new InetSocketAddress(8080), 0);
                        server.createContext("/", new Handler(router));
                        server.start();

                        System.out.println("Server running on http://localhost:8080");

                } catch (IOException e) {

                        e.printStackTrace();
                }
        }
}