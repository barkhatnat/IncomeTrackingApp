package ru.barkhatnat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.UpdateAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("accounts/{accountId:\\d+}")
public class AccountController {
    private final AccountService accountService;

    @ModelAttribute("account")
    public Account account(@PathVariable("accountId") int accountId) {
        Account account = accountService.findAccount(accountId).orElse(null);
        if (account == null) {
            throw new NoSuchElementException("accounts.errors.account.not_found");
        }
        return account;
    }

    @GetMapping
    public String getAccountInfo() {
        return "account-info";
    }

    @GetMapping("/edit")
    public String editAccountInfo() {
        return "account-edit";
    }

    @PostMapping("/save")
    public String saveAccount(@ModelAttribute("account") Account account, UpdateAccountPayload payload) {
        accountService.updateAccount(account.getId(), payload.title(), payload.balance());
//        return "redirect:/accounts/%d".formatted(account.getId());
        return "redirect:/accounts/list";
    }

    @PostMapping("/delete")
    public String deleteAccount(@ModelAttribute("account") Account account) {
        accountService.deleteAccount(account.getId());
        return "redirect:/accounts/list";
    }
}
