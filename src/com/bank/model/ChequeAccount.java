package com.bank.model;

public class ChequeAccount extends Account {
    private String employerName;
    private String employerAddress;

    public ChequeAccount(String accountNumber, double balance, Customer customer,
                         String branch, String employerName, String employerAddress) {
        super(accountNumber, balance, customer, branch);
        this.employerName = employerName;
        this.employerAddress = employerAddress;
    }

    @Override
    public boolean canWithdraw() {
        return true;
    }

    @Override
    public double calculateMonthlyInterest() {
        return 0; // No interest for cheque accounts
    }

    public String getEmployerName() { return employerName; }
    public String getEmployerAddress() { return employerAddress; }

    @Override
    public String toString() {
        return super.toString() + ", Employer: " + employerName;
    }
}