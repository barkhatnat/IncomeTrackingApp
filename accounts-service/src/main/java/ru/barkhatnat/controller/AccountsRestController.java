package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.barkhatnat.controller.payload.NewAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsRestController {
    private final AccountService accountService;

    @GetMapping
    public Iterable<Account> getAccountsList() {
        return this.accountService.findAllAccounts();
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody NewAccountPayload payload,
                                           BindingResult bindingResult,
                                           UriComponentsBuilder uriComponentsBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            Account account = accountService.createAccount(payload.title(), payload.balance());
            return ResponseEntity.created(uriComponentsBuilder
                            .replacePath("/accounts/{accountId}")
                            .build(Map.of("accountId", account.getId())))
                            .body(account);
        }
    }
}
