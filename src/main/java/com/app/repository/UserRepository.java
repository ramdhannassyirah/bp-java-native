package com.app.repository;

import com.app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final List<User> users;

    private long nextId = 1;

    public UserRepository() {

        users = new ArrayList<>();

        users.add(
                new User(
                        nextId++,
                        "Ramdhan",
                        "ramdhan@example.com"
                )
        );

        users.add(
                new User(
                        nextId++,
                        "Budi",
                        "budi@example.com"
                )
        );
    }

    public List<User> findAll() {

        return users;
    }

    public Optional<User> findById(
            Long id
    ) {

        return users.stream()
                .filter(
                        user ->
                                user.getId()
                                        .equals(id)
                )
                .findFirst();
    }

    public User save(
            User user
    ) {

        user.setId(nextId++);

        users.add(user);

        return user;
    }

    public User update(
            Long id,
            String name,
            String email
    ) {

        Optional<User> result =
                findById(id);

        if (result.isEmpty()) {
            return null;
        }

        User user =
                result.get();

        user.setName(name);
        user.setEmail(email);

        return user;
    }

    public boolean delete(
            Long id
    ) {

        return users.removeIf(
                user ->
                        user.getId()
                                .equals(id)
        );
    }
}