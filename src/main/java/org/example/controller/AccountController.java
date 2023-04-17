package org.example.controller;

import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepo;

import java.util.ArrayList;

public class AccountController {
    AccountRepo accountRepo = new AccountRepo();
    ArrayList<Account> accountList = accountRepo.loadAccountsFromBase();

    public Integer addNewAccount() {
        Account newAccount = new Account();
        accountList.add(newAccount);
        return newAccount.getId();
    }

    public void showMeMyAccounts(User user){
        System.out.println("Ваши счета: ");
        if(user.getAccountIds().isEmpty()){
            System.out.println("У вас нет счетов.");
        }
        else {
            for (Integer accountNumber : user.getAccountIds()) {
                for (Account account : accountList) {
                    if (accountNumber == account.getId()) {
                        System.out.println("\nНомер счёта: " + account.getId());
                        System.out.println("Баланс счёта: " + account.getBalance());
                        break;
                    }
                }
            }
        }
    }

    public void saveAccountAndExit() {
        accountRepo.saveAccountsToBase(accountList);
    }
}
