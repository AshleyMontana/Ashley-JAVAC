package com.bank.model;

public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.0005; // 0.05%

    public SavingsAccount(String accountNumber, double balance, Customer customer, String branch) {
        super(accountNumber, balance, customer, branch);
    }

    @Override
    public boolean canWithdraw() {
        return false; // NO withdrawals from savings
    }

    @Override
    public double calculateMonthlyInterest() {
        return getBalance() * INTEREST_RATE;
    }

    @Override
    public String toString() {
        return super.toString() + " [No withdrawals allowed]";
    }
}