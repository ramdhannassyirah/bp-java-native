package com.app.validation;

import com.app.exception.ValidationException;

public class PaginationValidator {

    private static final int MAX_LIMIT = 100;

    public void validate(
            int page,
            int limit
    ) {

        if (page < 1) {

            throw new ValidationException(
                    "Page minimal 1"
            );
        }

        if (limit < 1) {

            throw new ValidationException(
                    "Limit minimal 1"
            );
        }

        if (limit > MAX_LIMIT) {

            throw new ValidationException(
                    "Limit maksimal " + MAX_LIMIT
            );
        }
    }
}