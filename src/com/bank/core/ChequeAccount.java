package com.bank.core;

import java.math.BigDecimal;

public class ChequeAccount extends AbstractAccount {
    private final String employerName;
    private final String employerAddress;

    public ChequeAccount(String branch, Customer owner, BigDecimal initialDeposit, String employerName, String employerAddress) {
        super(branch, owner, initialDeposit);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public void applyMonthlyInterest() {
        // No interest for cheque account
    }

    @Override
    public String getAccountType() { return "CHEQUE"; }

    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }
}
