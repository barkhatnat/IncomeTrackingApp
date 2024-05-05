package ru.barkhatnat.entity;

import java.sql.Timestamp;
import java.util.List;

public record User(int id, String username, String password, String email, Timestamp createdAt,
                   List<Account> accounts) {
}
