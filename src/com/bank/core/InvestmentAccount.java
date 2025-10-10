package com.bank.core;

import java.math.BigDecimal;

public class InvestmentAccount extends AbstractAccount {
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.05");

    public InvestmentAccount(String branch, Customer owner, BigDecimal initialDeposit) {
        super(branch, owner, initialDeposit);
        if (initialDeposit == null || initialDeposit.compareTo(new BigDecimal("500")) < 0) {
            throw new IllegalArgumentException("Initial deposit must be at least 500 BWP");
        }
    }

    @Override
    public void applyMonthlyInterest() {
        if (balance.compareTo(BigDecimal.ZERO) <= 0) return;
        BigDecimal interest = balance.multiply(MONTHLY_INTEREST_RATE);
        balance = balance.add(interest);
        transactions.add(new Transaction("INTEREST", interest, balance));
    }

    @Override
    public String getAccountType() { return "INVESTMENT"; }
}
