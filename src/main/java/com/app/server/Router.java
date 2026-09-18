package com.app.server;

import com.app.response.ApiResponse;
import com.sun.net.httpserver.HttpExchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

        private final List<Route> routes = new ArrayList<>();

        public void get(String path, RouteHandler handler) {
                add("GET", path, handler);
        }

        public void post(String path, RouteHandler handler) {
                add("POST", path, handler);
        }

        public void put(String path, RouteHandler handler) {
                add("PUT", path, handler);
        }

        public void delete(String path, RouteHandler handler) {
                add("DELETE", path, handler);
        }

        private void add(String method, String path, RouteHandler handler) {
                routes.add(new Route(method, path, handler));
        }

        public ApiResponse<?> handle(HttpExchange exchange) throws Exception {

                String method = exchange.getRequestMethod();

                String path = exchange.getRequestURI().getPath();

                for (Route route : routes) {

                        Map<String, String> params = match(route, method, path);

                        if (params == null) {
                                continue;
                        }

                        Request request = new Request(exchange, params);

                        return route.getHandler().handle(request);
                }

                return new ApiResponse<>(
                                false,
                                404,
                                "Route tidak ditemukan",
                                null);
        }

        private Map<String, String> match(Route route, String method, String requestPath) {

                if (!route.getMethod().equals(method)) {

                        return null;
                }

                String[] routeParts = splitPath(route.getPath());

                String[] requestParts = splitPath(requestPath);

                if (routeParts.length != requestParts.length) {

                        return null;
                }

                Map<String, String> params = new HashMap<>();

                for (int i = 0; i < routeParts.length; i++) {

                        String routePart = routeParts[i];

                        String requestPart = requestParts[i];

                        if (isParameter(routePart)) {

                                String paramName = routePart.substring(1, routePart.length() - 1);

                                params.put(paramName, requestPart);

                                continue;
                        }

                        if (!routePart.equals(requestPart)) {
                                return null;
                        }
                }

                return params;
        }

        private String[] splitPath(
                        String path) {

                if (path.equals("/")) {
                        return new String[0];
                }

                return path.substring(1)
                                .split("/");
        }

        private boolean isParameter(String part) {

                return part.startsWith("{") && part.endsWith("}");
        }
}