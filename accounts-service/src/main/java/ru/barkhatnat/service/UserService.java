package ru.barkhatnat.service;

import ru.barkhatnat.DTO.UserCreateDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.entity.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAllUsers();

    User createUser(UserCreateDto userCreateDto);

    Optional<User> findUser(int id);

    void updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(int id);
}
