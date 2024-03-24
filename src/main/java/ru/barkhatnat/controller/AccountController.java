package ru.barkhatnat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.barkhatnat.controller.payload.NewAccountPayload;
import ru.barkhatnat.controller.payload.UpdateAccountPayload;
import ru.barkhatnat.entity.Account;
import ru.barkhatnat.service.AccountService;

@Controller
@RequiredArgsConstructor
@RequestMapping("accounts/{accountId:\\d+}")
public class AccountController {
    private final AccountService accountService;
    @ModelAttribute("account")
    public Account account(@PathVariable("accountId") int accountId) {
        return accountService.getAccount(accountId);
    }
    @GetMapping
    public String getAccountInfo(){
        return "account-info";
    }
    @GetMapping("/edit")
    public String editAccountInfo(){
        return "account-edit";
    }
    @PostMapping("/save")
    public String saveAccount(@ModelAttribute("account") Account account, UpdateAccountPayload payload){
        accountService.saveAccount(account.getId(), payload.title(), payload.balance());
//        return "redirect:/accounts/%d".formatted(account.getId());
        return "redirect:/accounts/list";
    }
}
