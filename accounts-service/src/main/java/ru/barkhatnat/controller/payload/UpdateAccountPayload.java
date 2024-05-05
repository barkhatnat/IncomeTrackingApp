package ru.barkhatnat.controller.payload;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record UpdateAccountPayload(
        @Size(min = 1, max = 16, message = "{accounts.update.errors.invalid_title_size}")
        String title,
        @NotNull(message = "{accounts.update.errors.null_balance}")
        @DecimalMin(value = "-9999999999.99", message = "{accounts.update.errors.invalid_balance_size}")
        @DecimalMax(value = "9999999999.99", message = "{accounts.update.errors.invalid_balance_size}")
        BigDecimal balance) {
}
