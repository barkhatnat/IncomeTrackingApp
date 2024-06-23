package ru.barkhatnat.service;

import ru.barkhatnat.DTO.UserCreateDto;
import ru.barkhatnat.DTO.UserResponseDto;
import ru.barkhatnat.DTO.UserUpdateDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.Category;
import ru.barkhatnat.entity.User;
import ru.barkhatnat.exception.UserAlreadyExistsException;

import java.util.Optional;

public interface UserService {

    Iterable<Account> findAllUserAccounts(Integer userId);
    Iterable<Category> findAllUserCategories(Integer userId);

    UserResponseDto createUser(UserCreateDto userCreateDto) throws UserAlreadyExistsException;

    Optional<User> findUser(int id);

    void updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(int id);
}
