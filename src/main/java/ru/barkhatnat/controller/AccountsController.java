package ru.barkhatnat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.NewAccountPayload;
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

    @PostMapping("/save")
    public String saveAccount(NewAccountPayload payload) {
        accountService.createAccount(payload.title(), payload.balance());
        return "redirect:/accounts/list";
    }
}
