package ru.barkhatnat.entity;

import java.math.BigDecimal;

public record Operation(int id, BigDecimal amount, String note) {
}
