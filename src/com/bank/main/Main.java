package com.bank.main;

import com.bank.model.Customer;
import com.bank.model.SavingsAccount;
import com.bank.model.InvestmentAccount;
import com.bank.model.ChequeAccount;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BANKING SYSTEM TEST ===\n");

        // Create a customer
        Customer john = new Customer(1, "John", "Doe", "123 Main St");
        System.out.println("Created: " + john + "\n");

        // Create accounts (we'll create these classes next)
        System.out.println("Creating accounts...");

        // These will show errors until we create the classes
        SavingsAccount savings = new SavingsAccount("SA001", 1000, john, "Main Branch");
        InvestmentAccount investment = new InvestmentAccount("IA001", 300, john, "Main Branch");
        ChequeAccount cheque = new ChequeAccount("CA001", 5000, john, "Main Branch",
                "TechCorp", "789 Tech Park");

        System.out.println("\nJohn's Accounts:");
        System.out.println("1. " + savings);
        System.out.println("2. " + investment);
        System.out.println("3. " + cheque);

        System.out.println("\n=== TEST STARTED ===");
    }
}