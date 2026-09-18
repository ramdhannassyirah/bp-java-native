package com.app.validation;

import com.app.exception.ValidationException;
import com.app.model.HelloRequest;

public class HelloValidator {

    public void validate(HelloRequest request) {

        if (request == null) {

            throw new ValidationException(
                    "Request tidak boleh kosong"
            );
        }

        if (request.getMessage() == null) {

            throw new ValidationException(
                    "Message wajib diisi"
            );
        }

        if (request.getMessage().isBlank()) {

            throw new ValidationException(
                    "Message tidak boleh kosong"
            );
        }
    }
}