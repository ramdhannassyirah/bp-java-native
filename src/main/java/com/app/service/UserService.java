package com.app.service;

import com.app.model.User;
import com.app.model.UserRequest;
import com.app.repository.UserRepository;
import com.app.response.ApiResponse;
import com.app.validation.UserValidator;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService() {

        userRepository =
                new UserRepository();

        userValidator =
                new UserValidator();
    }

    // GET /users
    public ApiResponse<List<User>> findAll() {

        List<User> users =
                userRepository.findAll();

        return new ApiResponse<>(
                true,
                200,
                "Berhasil mengambil data user",
                users
        );
    }

    // GET /users/{id}
    public ApiResponse<User> findById(
            Long id
    ) {

        return userRepository
                .findById(id)
                .map(
                        user ->
                                new ApiResponse<>(
                                        true,
                                        200,
                                        "Berhasil mengambil data user",
                                        user
                                )
                )
                .orElseGet(
                        () ->
                                new ApiResponse<>(
                                        false,
                                        404,
                                        "User tidak ditemukan",
                                        null
                                )
                );
    }

    // POST /users
    public ApiResponse<User> create(
            UserRequest request
    ) {

        userValidator.validate(request);

        User user =
                new User(
                        null,
                        request.getName(),
                        request.getEmail()
                );

        User savedUser =
                userRepository.save(user);

        return new ApiResponse<>(
                true,
                201,
                "Berhasil membuat user",
                savedUser
        );
    }

    // PUT /users/{id}
    public ApiResponse<User> update(
            Long id,
            UserRequest request
    ) {

        userValidator.validate(request);

        User updatedUser =
                userRepository.update(
                        id,
                        request.getName(),
                        request.getEmail()
                );

        if (updatedUser == null) {

            return new ApiResponse<>(
                    false,
                    404,
                    "User tidak ditemukan",
                    null
            );
        }

        return new ApiResponse<>(
                true,
                200,
                "Berhasil memperbarui user",
                updatedUser
        );
    }

    // DELETE /users/{id}
    public ApiResponse<Void> delete(
            Long id
    ) {

        boolean deleted =
                userRepository.delete(id);

        if (!deleted) {

            return new ApiResponse<>(
                    false,
                    404,
                    "User tidak ditemukan",
                    null
            );
        }

        return new ApiResponse<>(
                true,
                200,
                "Berhasil menghapus user",
                null
        );
    }
}