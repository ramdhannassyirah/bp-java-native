package com.app.database;

import java.sql.Connection;

public class DatabaseTest {

    public static void main(String[] args) {

        try {

            Connection connection =
                    DatabaseConnection.getConnection();

            System.out.println(
                    "Database berhasil terhubung!"
            );

            connection.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}