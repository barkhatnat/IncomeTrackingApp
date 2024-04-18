package ru.barkhatnat.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.NewAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountsController {
    private final AccountService accountService;

    @GetMapping("/list")
    public String getAccountsList(Model model) {
        model.addAttribute("accounts", accountService.findAllAccounts());
        return "account-list";
    }

    @GetMapping("/create")
    public String createAccount() {
        return "account-creation";
    }

    @PostMapping("/create")
    public String saveAccount(@Valid NewAccountPayload payload, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "account-creation";
        } else {
            Account account = accountService.createAccount(payload.title(), payload.balance());
//        return "redirect:/accounts/%d".formatted(account.getId());
            return "redirect:/accounts/list";
            //TODO после реализации списка операций (сущность аккаунт) сделать редирект на этот список
        }

    }
}
