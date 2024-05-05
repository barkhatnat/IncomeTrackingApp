package ru.barkhatnat.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record Operation(int id, BigDecimal amount, Timestamp datePurchase, Boolean operationType, String note,
                        Account account, Timestamp createdAt) {
}
