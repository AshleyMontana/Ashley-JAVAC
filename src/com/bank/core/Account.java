package com.bank.core;

import java.math.BigDecimal;
import java.util.List;

public interface Account {
    String getAccountNumber();
    BigDecimal getBalance();
    String getAccountType();
    void deposit(BigDecimal amount);
    boolean withdraw(BigDecimal amount);
    void applyMonthlyInterest();
    List<Transaction> getTransactions();
}
