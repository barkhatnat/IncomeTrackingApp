package ru.barkhatnat.service;

import ru.barkhatnat.DTO.UserCreateDto;
import ru.barkhatnat.DTO.UserResponseDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.exception.UserAlreadyExistsException;

import java.util.Optional;

public interface UserService {

    Iterable<Account> findAllUserAccounts(User user);

    UserResponseDto createUser(UserCreateDto userCreateDto) throws UserAlreadyExistsException;

    Optional<User> findUser(int id);

    void updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(int id);
}
