package com.bank.core;

import java.math.BigDecimal;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("=== Banking Core Model Demo ===");

        Customer ashley = new Customer("Ashley", "Montana", "12 Main St", "ashley@example.com", "pass123");
        SavingsAccount savings = new SavingsAccount("Gaborone", ashley, new BigDecimal("1000"));
        InvestmentAccount invest = new InvestmentAccount("Gaborone", ashley, new BigDecimal("500"));
        ChequeAccount cheque = new ChequeAccount("Gaborone", ashley, new BigDecimal("200"), "Acme Ltd", "1 Industrial Rd");

        ashley.addAccount(savings);
        ashley.addAccount(invest);
        ashley.addAccount(cheque);

        savings.deposit(new BigDecimal("200"));
        invest.deposit(new BigDecimal("500"));
        invest.withdraw(new BigDecimal("100"));
        savings.applyMonthlyInterest();
        invest.applyMonthlyInterest();

        System.out.println(ashley);
        for (Account acc : ashley.getAccounts()) {
            System.out.println(acc);
            acc.getTransactions().forEach(tx -> System.out.println("   " + tx));
        }

        System.out.println("=== Demo Complete ===");
    }
}
