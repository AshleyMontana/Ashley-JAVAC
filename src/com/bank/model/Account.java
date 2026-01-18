package com.bank.model;

public abstract class Account {
    private String accountNumber;
    private double balance;
    private Customer customer;
    private String branch;

    public Account(String accountNumber, double balance, Customer customer, String branch) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
        this.branch = branch;
        customer.addAccount(this);
    }

    public abstract boolean canWithdraw();
    public abstract double calculateMonthlyInterest();

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: BWP " + amount + " to " + accountNumber);
        }
    }

    public boolean withdraw(double amount) {
        if (canWithdraw() && amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: BWP " + amount + " from " + accountNumber);
            return true;
        }
        System.out.println("Withdrawal failed for " + accountNumber);
        return false;
    }

    public void payInterest() {
        double interest = calculateMonthlyInterest();
        balance += interest;
        System.out.println("Interest paid: BWP " + interest + " to " + accountNumber);
    }

    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public Customer getCustomer() { return customer; }
    public String getBranch() { return branch; }
    public String getAccountType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getAccountType() + " [" + accountNumber + "], Balance: BWP " + balance;
    }
}