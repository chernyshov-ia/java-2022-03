package ru.otus.dao;

import ru.otus.model.User;

import java.util.*;

public class InMemoryUserDao implements UserDao {

    private final Map<Long, User> users;

    public InMemoryUserDao() {
        users = new HashMap<>();
        users.put(1L, new User(1L, "Администратор", "admin", "11111"));
        users.put(2L, new User(2L, "Суперюзер", "superuser", "11111"));
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return users.values().stream().filter(v -> v.getLogin().equals(login)).findFirst();
    }
}
