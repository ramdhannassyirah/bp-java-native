package com.app.exception;

import com.app.response.ApiResponse;
import com.app.response.ResponseUtil;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;

public class ErrorHandler {

        public static void handle(HttpExchange exchange, Exception exception) throws IOException {

                if (exception instanceof ValidationException) {

                        ResponseUtil.send(exchange, new ApiResponse<>(false, 400, exception.getMessage(), null));
                        return;
                }

                if (exception instanceof DatabaseException) {

                        exception.printStackTrace();
                        ResponseUtil.send(exchange, new ApiResponse<>(false, 500, "Terjadi kesalahan database", null));
                        return;
                }

                if (exception instanceof NumberFormatException) {

                        ResponseUtil.send(exchange, new ApiResponse<>(false, 400, "Parameter tidak valid", null));
                        return;
                }

                exception.printStackTrace();

                ResponseUtil.send(exchange, new ApiResponse<>(false, 500, "Internal server error", null));
        }
}