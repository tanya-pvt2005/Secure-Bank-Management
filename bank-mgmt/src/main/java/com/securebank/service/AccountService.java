package com.securebank.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securebank.model.Account;
import com.securebank.model.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void createAccount(Long userId, String type, double initialBalance) {
        User user = getSession().find(User.class, userId);
        if (user == null) {
            System.out.println("User not found with ID: " + userId);
            return;
        }

        Account account = new Account();
        account.setUser(user);
        account.setType(type);
        account.setBalance(initialBalance);

        getSession().persist(account);
        System.out.println("Account created successfully with ID: " + account.getAccountId());
    }

    public void deposit(Long accountId, double amount) {
        Account account = getSession().find(Account.class, accountId);
        if (account == null) {
            System.out.println("Account not found with ID: " + accountId);
            return;
        }

        account.setBalance(account.getBalance() + amount);
        getSession().merge(account);
        System.out.println("Deposited " + amount + " to account ID: " + accountId);
    }

    public void withdraw(Long accountId, double amount) {
        Account account = getSession().find(Account.class, accountId);
        if (account == null) {
            System.out.println("Account not found with ID: " + accountId);
            return;
        }

        if (account.getBalance() < amount) {
            System.out.println("Insufficient balance in account ID: " + accountId);
            return;
        }

        account.setBalance(account.getBalance() - amount);
        getSession().merge(account);
        System.out.println("Withdrew " + amount + " from account ID: " + accountId);
    }

    public List<Account> getAccountsByUserId(Long userId) {
        return getSession()
                .createQuery("FROM Account a WHERE a.user.id = :uid", Account.class)
                .setParameter("uid", userId)
                .list();
    }
}
