package com.securebank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.securebank.model.Account;
import com.securebank.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService = new AccountService();

    public void createAccount(Long userId, String type, double initialBalance) {
        accountService.createAccount(userId, type, initialBalance);
        System.out.println();
    }

    public void deposit(Long accountId, double amount) {
        accountService.deposit(accountId, amount);
        System.out.println();
    }

    public void withdraw(Long accountId, double amount) {
        accountService.withdraw(accountId, amount);
        System.out.println();
    }

    public void viewAccounts(Long userId) {
        List<Account> accounts = accountService.getAccountsByUserId(userId);
        if (accounts.isEmpty()) {
            System.out.println("No accounts found for user id: " + userId);
        } else {
            for (Account acc : accounts) {
                System.out.println("Account ID: " + acc.getAccountId() + ", Type: " + acc.getType() + ", Balance: " + acc.getBalance());
            }
        }
        System.out.println();
    }
}
