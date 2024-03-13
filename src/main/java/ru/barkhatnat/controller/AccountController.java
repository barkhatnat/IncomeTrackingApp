package ru.barkhatnat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.barkhatnat.service.AccountService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/list")
    public String getAccountsList(Model model){
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "account-list";
    }
}
