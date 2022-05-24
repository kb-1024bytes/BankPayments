package com.company;

import java.util.Calendar;

public class Customer {
    private int balance;
    private int number_of_withdrawals;
    private int number_of_deposits;
    private int account_number;
    Calendar calendar;
    private int dayOfMonth;

    private String name;

    Customer(String name) {
        this.name = name;
        calendar = Calendar.getInstance();
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        balance = 0;
        number_of_deposits = 0;
        number_of_withdrawals = 0;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public int getNumber_of_deposits() {
        return number_of_deposits;
    }

    public void setNumber_of_deposits(int number_of_deposits) {
        this.number_of_deposits = number_of_deposits;
    }

    public int getNumber_of_withdrawals() {
        return number_of_withdrawals;
    }

    public void setNumber_of_withdrawals(int number_of_withdrawals) {
        this.number_of_withdrawals = number_of_withdrawals;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setDayOfMonth() {
        this.dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public String getName() {
        return name;
    }
}