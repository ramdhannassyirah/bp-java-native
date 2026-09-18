package com.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            throw new SQLException("PostgreSQL JDBC Driver tidak ditemukan", e);
        }

        return DriverManager.getConnection(
                DatabaseConfig.getUrl(),
                DatabaseConfig.getUsername(),
                DatabaseConfig.getPassword());
    }
}