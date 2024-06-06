package ru.barkhatnat.DTO;

import java.math.BigDecimal;

public record AccountResponseDto (Integer id, String title, BigDecimal balance) {
}
