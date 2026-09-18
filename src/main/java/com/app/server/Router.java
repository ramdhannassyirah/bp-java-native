package com.app.server;

import com.sun.net.httpserver.HttpServer;

public class Router {

    private final HttpServer server;

    public Router(HttpServer server) {
        this.server = server;
    }

    public void registerRoutes() {

        server.createContext("/", new Handler());
        server.createContext("/hello", new Handler());

    }
}