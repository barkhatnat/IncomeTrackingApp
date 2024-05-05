package ru.barkhatnat.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public record Account(int id, String title, BigDecimal balance, User user, Timestamp createdAt,
                      List<Operation> operations) {
};
