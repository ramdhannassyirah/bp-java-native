package com.app.service;

import com.app.model.HelloRequest;
import com.app.model.HelloResponse;
import com.app.response.ApiResponse;

public class HelloService {

    public ApiResponse<HelloResponse> getHello() {

        HelloResponse data =
                new HelloResponse(
                        "Hello from Service"
                );

        return new ApiResponse<>(
                true,
                200,
                "Berhasil mengambil data",
                data
        );
    }

    public ApiResponse<HelloResponse> postHello(
            HelloRequest request
    ) {

        HelloResponse data =
                new HelloResponse(
                        request.getMessage()
                );

        return new ApiResponse<>(
                true,
                201,
                "Berhasil membuat data",
                data
        );
    }

    public ApiResponse<HelloResponse> putHello() {

        HelloResponse data =
                new HelloResponse(
                        "PUT Hello from Service"
                );

        return new ApiResponse<>(
                true,
                200,
                "Berhasil memperbarui data",
                data
        );
    }

    public ApiResponse<HelloResponse> deleteHello() {

        HelloResponse data =
                new HelloResponse(
                        "DELETE Hello from Service"
                );

        return new ApiResponse<>(
                true,
                200,
                "Berhasil menghapus data",
                data
        );
    }
}