package ru.barkhatnat.DTO;


import java.math.BigDecimal;
import java.sql.Timestamp;

public record OperationResponseDto(Integer id, BigDecimal amount, Timestamp
        datePurchase, Integer categoryId, String note) {
}
