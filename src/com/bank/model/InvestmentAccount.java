package com.bank.model;

public class InvestmentAccount extends Account {
    private static final double INTEREST_RATE = 0.05; // 5%
    private static final double MIN_BALANCE = 500.0;

    public InvestmentAccount(String accountNumber, double balance, Customer customer, String branch) {
        super(accountNumber, Math.max(balance, MIN_BALANCE), customer, branch);
        if (balance < MIN_BALANCE) {
            System.out.println("Warning: Investment account opened with BWP 500 minimum");
        }
    }

    @Override
    public boolean canWithdraw() {
        return true;
    }

    @Override
    public double calculateMonthlyInterest() {
        return getBalance() * INTEREST_RATE;
    }

    @Override
    public String toString() {
        return super.toString() + " [Min: BWP 500, Interest: 5% monthly]";
    }
}