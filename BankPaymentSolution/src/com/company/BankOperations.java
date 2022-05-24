package com.company;

import java.util.HashMap;

public class BankOperations {
    private Bank m_bank;

    BankOperations() {
        m_bank = Bank.getInstance();
    }

    Customer addNewCustomer(String name) {
        Customer customer = new Customer(name);
        customer.setDayOfMonth();
        m_bank.assignAccountNumber(customer);
        m_bank.addCustomer(customer.getAccount_number(), customer);
        return customer;
    }

    public boolean deposit(int account_number, int deposit_amt) {
        HashMap<Integer, Customer> customerMap = m_bank.getCustomerMap();
        Customer currentCustomer = customerMap.get(account_number);
        if (null != currentCustomer) {
            if (deposit_amt < m_bank.MIN_DEPOSIT_AMOUNT) {
                System.out.println("Minimum deposit amount is " + m_bank.MIN_DEPOSIT_AMOUNT);
                return false;
            }
            if (deposit_amt >= m_bank.MAX_DEPOSIT_AMOUNT) {
                System.out.println("Maximum deposit amount is " + m_bank.MAX_DEPOSIT_AMOUNT);
                return false;
            }
            if (deposit_amt + currentCustomer.getBalance() > m_bank.MAX_BALANCE) {
                System.out.println("Maximum balance overflow (Limit = " + m_bank.MAX_BALANCE + ")");
                return false;
            }
            if (currentCustomer.getDayOfMonth() == m_bank.getDay()) {
                if (currentCustomer.getNumber_of_deposits() >= m_bank.MAX_TRANSACTIONS_PER_DAY) {
                    System.out.println("Only " + m_bank.MAX_TRANSACTIONS_PER_DAY + " deposits are allowed in a day");
                    return false;
                }
            } else {
                currentCustomer.setDayOfMonth(m_bank.getDay());
                currentCustomer.setNumber_of_deposits(0);
                currentCustomer.setNumber_of_withdrawals(0);
            }
            currentCustomer.setBalance(currentCustomer.getBalance() + deposit_amt);
            int number_of_deposits = currentCustomer.getNumber_of_deposits();
            currentCustomer.setNumber_of_deposits(number_of_deposits + 1);
            m_bank.updateCustomerData(currentCustomer);
            System.out.println(currentCustomer.getBalance());
            return true;
        } else {
            System.out.println("Invalid account number");
            return false;
        }
    }

    public void balance(int account_number) {
        HashMap<Integer, Customer> customerMap = m_bank.getCustomerMap();
        Customer currentCustomer = customerMap.get(account_number);
        if (null != currentCustomer) {
            System.out.println(currentCustomer.getBalance());
        }
    }

    public boolean withdraw(int account_number, int withdrawal_amt) {
        HashMap<Integer, Customer> customerMap = m_bank.getCustomerMap();
        Customer currentCustomer = customerMap.get(account_number);
        if (null != currentCustomer) {
            if (withdrawal_amt < m_bank.MIN_WITHDRAWAL_AMOUNT) {
                System.out.println("Minimum withdrawal amount is " + m_bank.MIN_WITHDRAWAL_AMOUNT);
                return false;
            }
            if (withdrawal_amt >= m_bank.MAX_WITHDRAWAL_AMOUNT) {
                System.out.println("Maximum withdrawal amount is " + m_bank.MAX_WITHDRAWAL_AMOUNT);
                return false;
            }
            if (currentCustomer.getBalance() < withdrawal_amt) {
                System.out.println("Maximum withdrawal amount is " + currentCustomer.getBalance() + " for account " + currentCustomer.getAccount_number());
                return false;
            }

            if (currentCustomer.getDayOfMonth() == m_bank.getDay()) {
                if (currentCustomer.getNumber_of_withdrawals() >= m_bank.MAX_TRANSACTIONS_PER_DAY) {
                    System.out.println("Only " + m_bank.MAX_TRANSACTIONS_PER_DAY + " withdrawals are allowed in a day");
                    return false;
                }
            } else {
                currentCustomer.setDayOfMonth(m_bank.getDay());
                currentCustomer.setNumber_of_deposits(0);
                currentCustomer.setNumber_of_withdrawals(0);
            }

            currentCustomer.setBalance(currentCustomer.getBalance() - withdrawal_amt);
            int number_of_withdrawals = currentCustomer.getNumber_of_withdrawals();
            currentCustomer.setNumber_of_withdrawals(number_of_withdrawals + 1);
            m_bank.updateCustomerData(currentCustomer);
            System.out.println(currentCustomer.getBalance());
            return true;
        } else {
            System.out.println("Invalid account number");
            return false;
        }
    }

    public void transfer(int src_account_number, int target_account_number, int amount) {
        if (m_bank.getCustomerMap().containsKey(src_account_number) && m_bank.getCustomerMap().containsKey(target_account_number)) {
            if (withdraw(src_account_number, amount)) {
                if (deposit(target_account_number, amount)) {
                    System.out.println("Successful");
                }
            }
        } else {
            System.out.println("Invalid account number");
        }
    }
}
