package com.app.validation;

import com.app.exception.ValidationException;
import com.app.model.UserRequest;

public class UserValidator {

    public void validate(
            UserRequest request) {

        if (request == null) {

            throw new ValidationException(
                    "Request tidak boleh kosong");
        }

        if (request.getName() == null
                || request.getName().isBlank()) {

            throw new ValidationException(
                    "Name wajib diisi");
        }

        if (request.getEmail() == null
                || request.getEmail().isBlank()) {

            throw new ValidationException(
                    "Email wajib diisi");
        }
    }
}