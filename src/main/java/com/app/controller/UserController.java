package com.app.controller;

import com.app.model.User;
import com.app.model.UserRequest;
import com.app.response.ApiResponse;
import com.app.server.Request;
import com.app.service.UserService;

import java.util.List;

public class UserController {

    private final UserService userService;

    public UserController() {

        userService =
                new UserService();
    }

    public ApiResponse<List<User>> findAll(
            Request request
    ) {

        return userService.findAll();
    }

    public ApiResponse<User> findById(
            Request request
    ) {

        Long id = Long.parseLong(
                request.param("id")
        );

        return userService.findById(id);
    }

    public ApiResponse<User> create(
            Request request
    ) throws Exception {

        UserRequest body =
                request.body(
                        UserRequest.class
                );

        return userService.create(body);
    }

    public ApiResponse<User> update(
            Request request
    ) throws Exception {

        Long id = Long.parseLong(
                request.param("id")
        );

        UserRequest body =
                request.body(
                        UserRequest.class
                );

        return userService.update(
                id,
                body
        );
    }

    public ApiResponse<Void> delete(
            Request request
    ) {

        Long id = Long.parseLong(
                request.param("id")
        );

        return userService.delete(id);
    }
}