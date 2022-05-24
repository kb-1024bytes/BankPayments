package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class InitData {

    int MINUTES = 1;
    Timer timer = new Timer();

    Bank m_bank;

    InitData() {
        m_bank = Bank.getInstance();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar cal = Calendar.getInstance();
                int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                if (m_bank.getDay() != dayOfMonth) {
                    m_bank.setDay(dayOfMonth);
                }
            }
        }, 0, 1000 * 60 * MINUTES);
        startOperations();
    }

    private void startOperations() {
        BankOperations bankOperations = new BankOperations();
        FileReader fr;
        BufferedReader br;
        try {
            // change absolute path of input.txt accordingly
            fr = new FileReader("C:\\Java codes\\BankPaymentSolution\\src\\com\\company\\input");
            br = new BufferedReader(fr);

            while (true) {
                try {
                    String input;
                    input = br.readLine();
                    if (null == input) break;
                    if (input.startsWith("Create")) {
                        String name = input.substring(8, input.length() - 1);
                        System.out.println(bankOperations.addNewCustomer(name).getAccount_number());
                    } else if (input.startsWith("Deposit")) {
                        String[] data = input.split(" ");
                        int account_number = Integer.parseInt(data[1]);
                        int deposit_amt = Integer.parseInt(data[2]);
                        bankOperations.deposit(account_number, deposit_amt);
                    } else if (input.startsWith("Balance")) {
                        String[] data = input.split(" ");
                        int account_number = Integer.parseInt(data[1]);
                        bankOperations.balance(account_number);
                    } else if (input.startsWith("Withdraw")) {
                        String[] data = input.split(" ");
                        int account_number = Integer.parseInt(data[1]);
                        int withdrawal_amt = Integer.parseInt(data[2]);
                        bankOperations.withdraw(account_number, withdrawal_amt);
                    } else if (input.startsWith("Transfer")) {
                        String[] data = input.split(" ");
                        int src_account_number = Integer.parseInt(data[1]);
                        int target_account_number = Integer.parseInt(data[2]);
                        int amount = Integer.parseInt(data[3]);
                        bankOperations.transfer(src_account_number, target_account_number, amount);
                    }
                } catch (Exception e) {
                    System.out.println("Enter valid command");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
