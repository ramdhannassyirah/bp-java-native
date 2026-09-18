package com.app.repository;

import com.app.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository
                extends BaseRepository {

        // =========================
        // COUNT
        // =========================

        public long count() {

                String sql = "SELECT COUNT(*) FROM users";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql);

                                ResultSet result = statement.executeQuery()) {

                        if (result.next()) {

                                return result.getLong(1);
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal menghitung jumlah user", e);
                }

                return 0;
        }

        // =========================
        // FIND ALL PAGINATION
        // =========================

        public List<User> findAll(
                        int page,
                        int limit) {

                List<User> users = new ArrayList<>();

                int offset = (page - 1) * limit;

                String sql = "SELECT id, name, email " +
                                "FROM users " +
                                "ORDER BY id " +
                                "LIMIT ? OFFSET ?";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setInt(1, limit);
                        statement.setInt(2, offset);

                        try (
                                        ResultSet result = statement.executeQuery()) {

                                while (result.next()) {

                                        User user = new User(
                                                        result.getLong("id"),
                                                        result.getString("name"),
                                                        result.getString("email"));

                                        users.add(user);
                                }
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal mengambil data user",
                                        e);
                }

                return users;
        }

        // =========================
        // FIND ALL
        // =========================

        public List<User> findAll() {

                List<User> users = new ArrayList<>();

                String sql = "SELECT id, name, email FROM users";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql);

                                ResultSet result = statement.executeQuery()) {

                        while (result.next()) {

                                User user = new User(
                                                result.getLong("id"),
                                                result.getString("name"),
                                                result.getString("email"));

                                users.add(user);
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal mengambil data user",
                                        e);
                }

                return users;
        }

        // =========================
        // FIND BY ID
        // =========================

        public Optional<User> findById(
                        Long id) {

                String sql = "SELECT id, name, email " +
                                "FROM users " +
                                "WHERE id = ?";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        setLong(
                                        statement,
                                        1,
                                        id);

                        try (
                                        ResultSet result = statement.executeQuery()) {

                                if (result.next()) {

                                        User user = new User(
                                                        result.getLong("id"),
                                                        result.getString("name"),
                                                        result.getString("email"));

                                        return Optional.of(user);
                                }
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal mengambil data user",
                                        e);
                }

                return Optional.empty();
        }

        // =========================
        // SAVE
        // =========================

        public User save(
                        User user) {

                String sql = "INSERT INTO users (name, email) " +
                                "VALUES (?, ?) " +
                                "RETURNING id";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        setString(
                                        statement,
                                        1,
                                        user.getName());

                        setString(
                                        statement,
                                        2,
                                        user.getEmail());

                        try (
                                        ResultSet result = statement.executeQuery()) {

                                if (result.next()) {

                                        user.setId(
                                                        result.getLong("id"));
                                }
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal membuat user",
                                        e);
                }

                return user;
        }

        // =========================
        // UPDATE
        // =========================

        public User update(
                        Long id,
                        String name,
                        String email) {

                String sql = "UPDATE users " +
                                "SET name = ?, email = ? " +
                                "WHERE id = ? " +
                                "RETURNING id, name, email";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        setString(
                                        statement,
                                        1,
                                        name);

                        setString(
                                        statement,
                                        2,
                                        email);

                        setLong(
                                        statement,
                                        3,
                                        id);

                        try (
                                        ResultSet result = statement.executeQuery()) {

                                if (result.next()) {

                                        return new User(
                                                        result.getLong("id"),
                                                        result.getString("name"),
                                                        result.getString("email"));
                                }
                        }

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal memperbarui user",
                                        e);
                }

                return null;
        }

        // =========================
        // DELETE
        // =========================

        public boolean delete(
                        Long id) {

                String sql = "DELETE FROM users " +
                                "WHERE id = ?";

                try (
                                Connection connection = getConnection();

                                PreparedStatement statement = connection.prepareStatement(sql)) {

                        setLong(
                                        statement,
                                        1,
                                        id);

                        int affectedRows = statement.executeUpdate();

                        return affectedRows > 0;

                } catch (Exception e) {

                        throw databaseError(
                                        "Gagal menghapus user",
                                        e);
                }
        }
}