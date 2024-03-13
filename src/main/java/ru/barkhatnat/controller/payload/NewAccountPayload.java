package ru.barkhatnat.controller.payload;

import java.math.BigDecimal;

public record NewAccountPayload (String title, BigDecimal balance) {
}
