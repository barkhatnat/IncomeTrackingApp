package ru.barkhatnat.service;

import ru.barkhatnat.DTO.*;
import ru.barkhatnat.entity.Operation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public interface OperationService {

    Iterable<Operation> findAllOperations();
    Iterable<Operation> findAllAccountOperations(Integer accountId);

    OperationResponseDto createOperation(OperationDto operationDto, Integer currentAccountId);

    Optional<Operation> findOperation(int id);

    void updateOperation(Integer id, BigDecimal amount, Timestamp datePurchase, Integer categoryId, String note, Integer currentAccountId);

    void deleteOperation(int id, Integer currentAccountId);
}
