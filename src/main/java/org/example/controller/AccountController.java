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

    public void deleteAccount(Account account) {
        accountList.remove(account);
    }

    public void showMeMyAccounts(User user){
        for (Integer accountNumber : user.getAccountIds()) {
            for (Account account : accountList) {
                if (accountNumber == account.getId()) {
                    System.out.println("Номер счёта: " + account.getId());
                    System.out.println("Баланс счёта: " + account.getBalance() + "\n");
                    break;
                }
            }
        }
    }
    public Account findAccountByNumber(Integer numberOfAccount) {
        for(Account account: accountList) {
            if(!accountList.isEmpty() && account.getId() == numberOfAccount)
                return account;
        }
        System.out.println("Счёт с таким номером не найден");
        return null;
    }

    public boolean isMyAccount(User user, Account account) {
        if(account != null)
            return user.getAccountIds().contains(account.getId());
        else
            return false;
    }

    public void saveAccountAndExit() {
        accountRepo.saveAccountsToBase(accountList);
    }
}
