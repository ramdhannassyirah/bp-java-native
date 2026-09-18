package com.app.controller;

import com.app.model.Pagination;
import com.app.model.User;
import com.app.model.UserRequest;
import com.app.response.ApiResponse;
import com.app.server.Request;
import com.app.service.UserService;
import com.app.validation.PaginationValidator;

public class UserController {

        private final UserService userService;
        private final PaginationValidator paginationValidator;

        public UserController() {
                userService = new UserService();
                paginationValidator = new PaginationValidator();
        }

        public ApiResponse<Pagination<User>> findAll(Request request) {

                String pageParam = request.query("page");
                String limitParam = request.query("limit");

                int page = 1;
                int limit = 10;

                if (pageParam != null) {

                        page = Integer.parseInt(pageParam);
                }

                if (limitParam != null) {

                        limit = Integer.parseInt(limitParam);
                }

                paginationValidator.validate(page, limit);

                return userService.findAll(page, limit);
        }

        public ApiResponse<User> findById(Request request) {

                Long id = Long.parseLong(request.param("id"));

                return userService.findById(id);
        }

        public ApiResponse<User> create(Request request) throws Exception {

                UserRequest body = request.body(UserRequest.class);

                return userService.create(body);
        }

        public ApiResponse<User> update(Request request) throws Exception {

                Long id = Long.parseLong(request.param("id"));

                UserRequest body = request.body(UserRequest.class);

                return userService.update(id, body);
        }

        public ApiResponse<Void> delete(Request request) {

                Long id = Long.parseLong(request.param("id"));

                return userService.delete(id);
        }
}