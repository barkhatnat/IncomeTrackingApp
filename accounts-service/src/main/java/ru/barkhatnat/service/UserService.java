package ru.barkhatnat.service;

import ru.barkhatnat.entity.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAllUsers();

    User createUser(String username, String password, String email, String role);

    Optional<User> findUser(int id);

    void updateUser(Integer id, String username, String password, String email);

    void deleteUser(int id);
}
