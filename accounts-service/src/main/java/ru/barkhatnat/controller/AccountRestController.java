package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.NewAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts/{accountId:\\d+}")
public class AccountRestController {
    private final AccountService accountService;
    private final MessageSource messageSource;

    @ModelAttribute("account")
    public Account account(@PathVariable("accountId") int accountId) {
        return this.accountService.findAccount(accountId).orElseThrow(() -> new NoSuchElementException("accounts.errors.account.not_found"));
    }

    @GetMapping
    public Account getAccount(@ModelAttribute("account") Account account) {
        return account;
    }

    @PatchMapping
    public ResponseEntity<?> updateAccount(@ModelAttribute("account") Account account, @Valid @RequestBody NewAccountPayload payload,
                                           BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        } else {
            this.accountService.updateAccount(account.getId(), payload.title(), payload.balance());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAccount(@ModelAttribute("account") Account account) {
        this.accountService.deleteAccount(account.getId());
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handleNoSuchElementException(NoSuchElementException e, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                        this.messageSource.getMessage(e.getMessage(), new Object[0],
                                e.getMessage(), locale)));
    }
}
