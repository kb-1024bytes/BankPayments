package com.company;

import java.util.Calendar;
import java.util.HashMap;

public class Bank {

    public final int MAX_BALANCE = 100000;
    public final int MIN_BALANCE = 0;
    public final int MIN_DEPOSIT_AMOUNT = 500;
    public final int MAX_DEPOSIT_AMOUNT = 50000;
    public final int MIN_WITHDRAWAL_AMOUNT = 1000;
    public final int MAX_WITHDRAWAL_AMOUNT = 25000;
    public final int MAX_TRANSACTIONS_PER_DAY = 3;
    private int ACC_NUMBER_START = 1000;
    private static Bank bankInstance = null;
    private static HashMap<Integer, Customer> customerMap;
    private static int day;

    private Bank() {
    }

    public static HashMap<Integer, Customer> getCustomerMap() {
        return customerMap;
    }

    public static Bank getInstance() {
        if (bankInstance == null) {
            bankInstance = new Bank();
            customerMap = new HashMap<>();
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        }
        return bankInstance;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        Bank.day = day;
    }


    public void assignAccountNumber(Customer customer) {
        ACC_NUMBER_START++;
        customer.setAccount_number(ACC_NUMBER_START);
    }

    public void addCustomer(int acc_number, Customer customer) {
        customerMap.put(acc_number, customer);
    }

    public void updateCustomerData(Customer currentCustomer) {
        customerMap.put(currentCustomer.getAccount_number(), currentCustomer);
    }
}