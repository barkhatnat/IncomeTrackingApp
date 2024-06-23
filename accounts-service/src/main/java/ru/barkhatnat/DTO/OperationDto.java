package ru.barkhatnat.DTO;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record OperationDto(
        @NotNull
        @DecimalMin(value = "-9999999999.99")
        @DecimalMax(value = "9999999999.99")
        BigDecimal amount,
        @NotNull
        Timestamp datePurchase,
        @NotNull
        Integer categoryId,
        @NotNull
        @Size(min = 1, max = 512)
        String note

) {
}
