package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.barkhatnat.DTO.AccountDto;
import ru.barkhatnat.DTO.AccountResponseDto;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;
import ru.barkhatnat.utils.AccountMapper;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsRestController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @GetMapping
    public ResponseEntity<Iterable<AccountResponseDto>> getAccountsList() {
        Iterable<Account> accounts = accountService.findAllAccounts();
        Iterable<AccountResponseDto> userResponseCollection = accountMapper.toAccountResponseDtoCollection(accounts);
        return ResponseEntity.ok(userResponseCollection);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDto accountDto,
                                           BindingResult bindingResult,
                                           UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Account account = accountService.createAccount(accountDto);
            AccountResponseDto accountResponseDto = accountMapper.toAccountResponseDto(account);
            return ResponseEntity.created(uriComponentsBuilder
                            .replacePath("/accounts/{accountId}")
                            .build(Map.of("accountId", account.getId())))
                    .body(accountResponseDto);
        }
    }
}
