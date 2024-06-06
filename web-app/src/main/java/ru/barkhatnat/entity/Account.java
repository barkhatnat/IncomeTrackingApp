package ru.barkhatnat.entity;

import java.math.BigDecimal;

public record Account(int id, String title, BigDecimal balance) {
}
