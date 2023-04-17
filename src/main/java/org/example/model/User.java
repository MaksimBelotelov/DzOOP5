package org.example.model;

import java.util.ArrayList;

public class User {
    private String surname;
    private String name;
    private String email;
    private String passWord;
    private ArrayList<Integer> accountIds;

    public User() {
    }

    public User(String surname, String name, String email, String passWord, ArrayList<Integer> accounts) {
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.passWord = passWord;
        this.accountIds = accounts;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ArrayList<Integer> getAccountIds() {
        return accountIds;
    }

    @Override
    public String toString() {
        return email + '\n' +
               surname + '\n' +
               name + '\n' +
               passWord + '\n' +
               accountIds.toString().replaceAll("^\\[|\\]$", "") + '\n';
    }
}
