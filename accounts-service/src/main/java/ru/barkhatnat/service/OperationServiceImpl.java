package ru.barkhatnat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.barkhatnat.DTO.OperationDto;
import ru.barkhatnat.DTO.OperationResponseDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.entity.Category;
import ru.barkhatnat.entity.Operation;
import ru.barkhatnat.repositories.OperationRepository;
import ru.barkhatnat.utils.OperationMapper;
import ru.barkhatnat.utils.SecurityUtil;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {

    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;
    private final CategoryService categoryService;
    private final AccountService accountService;


    @Override
    @Transactional
    public Iterable<Operation> findAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<Operation> findAllAccountOperations(Integer accountId) {
        Optional<Account> account = accountService.findAccount(accountId);
        if (account.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        if (!account.get().getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())){
            throw new IllegalArgumentException("You do not have permission to create operation for this account.");
        }
        return operationRepository.findOperationsByAccount_Id(accountId);
    }

    @Override
    @Transactional
    public OperationResponseDto createOperation(OperationDto operationDto, Integer currentAccountId) {
        Optional<Category> category = categoryService.findCategory(operationDto.categoryId());
        if (category.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        Optional<Account> account = accountService.findAccount(currentAccountId);
        if (account.isEmpty()) {
            throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
        }
        if (!account.get().getUser().getId().equals(SecurityUtil.getCurrentUserDetails().getUserId())){
            throw new IllegalArgumentException("You do not have permission to create operation for this account.");
        }
        Operation operation = operationRepository.save(new Operation(operationDto.amount(), operationDto.datePurchase(), category.get(), account.get(), operationDto.note(), getCreationDate()));
        return operationMapper.toOperationResponseDto(operation);

    }

    @Override
    @Transactional
    public Optional<Operation> findOperation(int id) {
        return operationRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateOperation(Integer id, BigDecimal amount, Timestamp datePurchase, Integer categoryId, String note, Integer currentAccountId) {
        operationRepository.findById(id).ifPresentOrElse(operation -> {
            if (operation.getAccount() != null && operation.getAccount().getId().equals(currentAccountId)) {
                Optional<Category> category = categoryService.findCategory(categoryId);
                if (category.isEmpty()) {
                    throw new NoSuchElementException(); //TODO сделать кастомный эксепшн
                }
                operation.setAmount(amount);
                operation.setDatePurchase(datePurchase);
                operation.setCategory(category.get());
                operation.setNote(note);
            } else {
                throw new IllegalArgumentException("You do not have permission to update this operation.");
            }
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    @Override
    @Transactional
    public void deleteOperation(int id, Integer currentAccountId) {
        operationRepository.findById(id).ifPresentOrElse(operation -> {
            if (operation.getAccount() != null && operation.getAccount().getId().equals(currentAccountId)) {
                operationRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("You do not have permission to update this operation.");
            }
        }, () -> {
            throw new NoSuchElementException();
        });
    }

    private Timestamp getCreationDate() {
        return Timestamp.from(Instant.now());
    }
}
