package com.bank.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractAccount implements Account {
    protected final String accountNumber;
    protected BigDecimal balance;
    protected final String branch;
    protected final Customer owner;
    protected final List<Transaction> transactions = new ArrayList<>();

    protected AbstractAccount(String branch, Customer owner, BigDecimal initialDeposit) {
        this.accountNumber = IdGenerator.nextId("A-");
        this.branch = branch;
        this.owner = owner;
        this.balance = initialDeposit == null ? BigDecimal.ZERO : initialDeposit;
        if (initialDeposit != null && initialDeposit.compareTo(BigDecimal.ZERO) > 0) {
            transactions.add(new Transaction("OPEN", initialDeposit, balance));
        }
    }

    @Override
    public String getAccountNumber() { return accountNumber; }

    @Override
    public BigDecimal getBalance() { return balance; }

    @Override
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Deposit amount must be positive");
        balance = balance.add(amount);
        transactions.add(new Transaction("DEPOSIT", amount, balance));
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Withdraw amount must be positive");
        if (balance.compareTo(amount) < 0)
            return false;
        balance = balance.subtract(amount);
        transactions.add(new Transaction("WITHDRAW", amount, balance));
        return true;
    }

    @Override
    public String toString() {
        String ownerName = owner == null ? "N/A" : owner.getFirstName() + " " + owner.getLastName();
        return String.format("%s[%s] - Owner: %s | Balance: %s",
                getAccountType(), accountNumber, ownerName, balance);
    }
}
