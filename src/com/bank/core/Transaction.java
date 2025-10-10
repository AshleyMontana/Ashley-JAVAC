package com.bank.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final String transactionId;
    private final LocalDateTime timestamp;
    private final String type;
    private final BigDecimal amount;
    private final BigDecimal balanceAfter;

    public Transaction(String type, BigDecimal amount, BigDecimal balanceAfter) {
        this.transactionId = IdGenerator.nextId("T-");
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | Balance: %s",
                transactionId, timestamp, type, amount, balanceAfter);
    }
}
