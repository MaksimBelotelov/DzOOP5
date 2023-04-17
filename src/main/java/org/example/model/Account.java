package org.example.model;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    public static Integer idCounter;
    private Integer id;
    private Double balance;

    public Account(Integer id, Double balance){
        this.id = id;
        this.balance = 0.0;
    }

    public Account() {
        this(idCounter++, 0.0);
    }

    public int getId() {
        return id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return id + "\n" +
               balance + '\n';
    }
}
