package ru.barkhatnat.controller.payload;

public record NewUserPayload(
        String username,
        String password,
        String email, String role) {
}
