package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.UpdateAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountRestClient;

import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts/{accountId:\\d+}")
public class AccountController {
    private final AccountRestClient accountRestClient;

    @ModelAttribute("account")
    public Account account(@PathVariable("accountId") int accountId) {
        Account account = accountRestClient.findAccount(accountId).orElse(null);
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

    @PostMapping("/edit")
    public String saveAccount(@ModelAttribute("account") Account account, @Valid UpdateAccountPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "account-edit";
        } else {
            accountRestClient.updateAccount(account.id(), payload.title(), payload.balance());
            return "redirect:/accounts";
        }
    }

    @PostMapping("/delete")
    public String deleteAccount(@ModelAttribute("account") Account account) {
        accountRestClient.deleteAccount(account.id());
        return "redirect:/accounts";
    }
}
