package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestClass {

    @Test
    void test_Init() {
        BankOperations bankOperations = new BankOperations();
        Bank bank = Bank.getInstance();
        int account_number = 0;
        bankOperations.addNewCustomer("ABCD");
        bankOperations.addNewCustomer("DEF");
        HashMap<Integer, Customer> customerHashMap = bank.getCustomerMap();
        assertTrue(customerHashMap.size() == 2);
        boolean containsABCD = false;
        boolean containsDEF = false;
        Iterator hmIterator = customerHashMap.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) hmIterator.next();
            Customer c = (Customer) mapElement.getValue();
            account_number = c.getAccount_number();
            if (c.getName() == "ABCD") {
                containsABCD = true;
            } else if (c.getName() == "DEF") {
                containsDEF = true;
            }
        }
        customerHashMap = bank.getCustomerMap();
        assertTrue(0 != account_number);
        assertTrue(containsABCD && containsDEF);

        bankOperations.deposit(account_number, 3000);
        assertTrue(customerHashMap.get(account_number).getBalance() == 3000);

        bankOperations.withdraw(account_number, 1000);
        customerHashMap = bank.getCustomerMap();
        assertTrue(customerHashMap.get(account_number).getBalance() == 2000);

        bankOperations.withdraw(account_number, 2001);
        customerHashMap = bank.getCustomerMap();
        assertTrue(customerHashMap.get(account_number).getBalance() == 2000);

        bankOperations.withdraw(account_number, 499);
        customerHashMap = bank.getCustomerMap();
        assertTrue(customerHashMap.get(account_number).getBalance() == 2000);

        bankOperations.transfer(account_number, 1001, 1500);
        customerHashMap = bank.getCustomerMap();
        assertTrue(customerHashMap.get(account_number).getBalance() == 500);
        assertTrue(customerHashMap.get(1001).getBalance() == 1500);
    }
}