package ru.barkhatnat.service;

import ru.barkhatnat.controller.payload.LoginResponse;
import ru.barkhatnat.entity.User;

public interface AuthRestClient {
    User registration(String username, String password, String email);

    LoginResponse login(String email, String password);
}
