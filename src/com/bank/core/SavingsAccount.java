package com.bank.core;

import java.math.BigDecimal;

public class SavingsAccount extends AbstractAccount {
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.0005");

    public SavingsAccount(String branch, Customer owner, BigDecimal initialDeposit) {
        super(branch, owner, initialDeposit);
    }

    @Override
    public void applyMonthlyInterest() {
        if (balance.compareTo(BigDecimal.ZERO) <= 0) return;
        BigDecimal interest = balance.multiply(MONTHLY_INTEREST_RATE);
        balance = balance.add(interest);
        transactions.add(new Transaction("INTEREST", interest, balance));
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        return false; // Savings cannot withdraw
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }
}
