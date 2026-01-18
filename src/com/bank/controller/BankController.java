package com.bank.controller;

import com.bank.model.*;
import java.util.ArrayList;
import java.util.List;

public class BankController {
    // YOUR empty lists - start fresh!
    private List<Customer> customers;
    private List<Account> accounts;
    private int nextCustomerId = 1;
    private int nextAccountNumber = 1000;

    public BankController() {
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
        System.out.println("✅ BankController initialized - Ready for YOUR data!");

        // NO AI customers - empty start!
    }

    // YOU create customers
    public Customer createCustomer(String firstName, String surname, String address) {
        // Simple validation
        if (firstName == null || firstName.trim().isEmpty() ||
                surname == null || surname.trim().isEmpty() ||
                address == null || address.trim().isEmpty()) {
            return null;
        }

        Customer customer = new Customer(nextCustomerId++, firstName, surname, address);
        customers.add(customer);
        return customer;
    }

    public Customer getCustomer(int customerId) {
        for (Customer c : customers) {
            if (c.getCustomerId() == customerId) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    // Open accounts
    public Account openSavingsAccount(int customerId, double initialDeposit, String branch) {
        Customer customer = getCustomer(customerId);
        if (customer == null) return null;

        String accNo = "SA" + (nextAccountNumber++);
        Account account = new SavingsAccount(accNo, initialDeposit, customer, branch);
        accounts.add(account);
        return account;
    }

    public Account openInvestmentAccount(int customerId, double initialDeposit, String branch) {
        Customer customer = getCustomer(customerId);
        if (customer == null) return null;

        String accNo = "IA" + (nextAccountNumber++);
        Account account = new InvestmentAccount(accNo, initialDeposit, customer, branch);
        accounts.add(account);
        return account;
    }

    public Account openChequeAccount(int customerId, double initialDeposit, String branch,
                                     String employerName, String employerAddress) {
        Customer customer = getCustomer(customerId);
        if (customer == null) return null;

        String accNo = "CA" + (nextAccountNumber++);
        Account account = new ChequeAccount(accNo, initialDeposit, customer, branch,
                employerName, employerAddress);
        accounts.add(account);
        return account;
    }

    // Transactions
    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null && amount > 0) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null && amount > 0) {
            return account.withdraw(amount);
        }
        return false;
    }

    // Queries
    public Account findAccount(String accountNumber) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts);
    }

    public List<Account> getCustomerAccounts(int customerId) {
        List<Account> result = new ArrayList<>();
        for (Account acc : accounts) {
            if (acc.getCustomer().getCustomerId() == customerId) {
                result.add(acc);
            }
        }
        return result;
    }

    public String getBankSummary() {
        double total = 0;
        for (Account acc : accounts) {
            total += acc.getBalance();
        }

        return "🏦 YOUR BANK SUMMARY\n" +
                "Customers: " + customers.size() + " (Created by YOU)\n" +
                "Accounts: " + accounts.size() + "\n" +
                "Total Balance: BWP " + String.format("%,.2f", total) + "\n\n" +
                "✅ Ready for assignment submission!";
    }
}