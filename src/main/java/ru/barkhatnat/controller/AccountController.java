package ru.barkhatnat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

import java.math.BigDecimal;

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
    @GetMapping("/{id}")
    public String getAccountsList(@PathVariable int id, Model model){
        model.addAttribute("account", accountService.getAccount(id));
        return "account";
    }
    @RequestMapping("/create")
    public String createAccount(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "account-creation";
    }
//    @PostMapping("/save")
//    public String saveAccount(@ModelAttribute("account") Account account){
//        accountService.saveAccount(account);
//        return "redirect:/accounts/list";
//    }
//    @PostMapping("/save")
//    public String saveAccount((@RequestParam Integer id, @RequestParam String title, @RequestParam BigDecimal balance){
//        Account account = new Account();
//        account.setId(id);
//        account.setTitle(title);
//        account.setBalance(balance);
//        accountService.saveAccount(account);
//        return "redirect:/accounts/list";
//    }
}
