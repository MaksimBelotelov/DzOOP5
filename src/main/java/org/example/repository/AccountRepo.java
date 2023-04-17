package org.example.repository;

import org.example.model.Account;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Integer.valueOf;

public class AccountRepo {
    public static final String ACCOUNTSPATH = "AccountsData.txt";

    public void saveAccountsToBase(ArrayList<Account> list) {
        try(FileWriter writer = new FileWriter(ACCOUNTSPATH, false)) {
            writer.append(Account.idCounter.toString() + '\n');
            for(Account account : list){
                writer.append(account.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Account> loadAccountsFromBase() {
        ArrayList<Account> accounts = new ArrayList<>();

        try {
            File file = new File(ACCOUNTSPATH);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            Account.idCounter = valueOf(reader.readLine());
            String id = reader.readLine();
            while (id != null){
                String balance = reader.readLine();
                accounts.add(new Account(valueOf(id), Double.valueOf(balance)));
                id = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return accounts;
    }
}
