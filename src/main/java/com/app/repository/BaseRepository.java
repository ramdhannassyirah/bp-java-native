package com.app.repository;

import com.app.database.DatabaseConnection;
import com.app.exception.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseRepository {

    protected Connection getConnection()
            throws SQLException {

        return DatabaseConnection.getConnection();
    }

    protected void setLong(
            PreparedStatement statement,
            int index,
            Long value
    ) throws SQLException {

        statement.setLong(index, value);
    }

    protected void setString(
            PreparedStatement statement,
            int index,
            String value
    ) throws SQLException {

        statement.setString(index, value);
    }

    protected DatabaseException databaseError(
            String message,
            Exception exception
    ) {

        return new DatabaseException(
                message,
                exception
        );
    }
}