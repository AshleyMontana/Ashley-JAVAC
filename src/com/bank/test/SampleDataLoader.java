package com.bank.test;

import com.bank.controller.BankController;
import com.bank.model.Account;
import com.bank.model.Customer;
import java.util.List;

public class SampleDataLoader {

    public static void main(String[] args) {
        System.out.println("📊 CREATING SAMPLE DATA FOR ASSIGNMENT SUBMISSION");
        System.out.println("=".repeat(50));

        BankController bank = new BankController();

        // Create 10 sample records (5 customers, 10 accounts)
        System.out.println("\n👥 Creating 5 customers...");

        Customer[] customers = new Customer[5];
        String[] firstNames = {"Thabo", "Lerato", "Kgosi", "Amogelang", "Tumelo"};
        String[] surnames = {"Moloi", "Williams", "Phiri", "Van Wyk", "Mokoena"};
        String[] addresses = {
                "123 Main St, Gaborone",
                "456 Oak Ave, Francistown",
                "789 Pine Rd, Maun",
                "321 Elm St, Palapye",
                "654 Maple Ave, Kasane"
        };

        for (int i = 0; i < 5; i++) {
            customers[i] = bank.createCustomer(firstNames[i], surnames[i], addresses[i]);
            System.out.println("   ✅ " + firstNames[i] + " " + surnames[i] + " (ID: " +
                    customers[i].getCustomerId() + ")");
        }

        System.out.println("\n💳 Creating 10 accounts...");

        // Customer 1: 2 accounts
        bank.openSavingsAccount(customers[0].getCustomerId(), 2500.0, "Main Branch");
        bank.openInvestmentAccount(customers[0].getCustomerId(), 5000.0, "Main Branch");
        System.out.println("   ✅ Thabo: Savings + Investment accounts");

        // Customer 2: 2 accounts
        bank.openChequeAccount(customers[1].getCustomerId(), 7500.0, "Mall Branch",
                "TechCorp Ltd", "789 Industrial Area");
        bank.openSavingsAccount(customers[1].getCustomerId(), 3000.0, "Mall Branch");
        System.out.println("   ✅ Lerato: Cheque + Savings accounts");

        // Customer 3: 2 accounts
        bank.openInvestmentAccount(customers[2].getCustomerId(), 6000.0, "North Branch");
        bank.openSavingsAccount(customers[2].getCustomerId(), 1500.0, "North Branch");
        System.out.println("   ✅ Kgosi: Investment + Savings accounts");

        // Customer 4: 2 accounts
        bank.openChequeAccount(customers[3].getCustomerId(), 4000.0, "South Branch",
                "Mining Co", "123 Mine Road");
        bank.openInvestmentAccount(customers[3].getCustomerId(), 3500.0, "South Branch");
        System.out.println("   ✅ Amogelang: Cheque + Investment accounts");

        // Customer 5: 2 accounts
        bank.openSavingsAccount(customers[4].getCustomerId(), 2000.0, "Airport Branch");
        bank.openChequeAccount(customers[4].getCustomerId(), 4500.0, "Airport Branch",
                "Tourism Board", "456 Tourism Centre");
        System.out.println("   ✅ Tumelo: Savings + Cheque accounts");

        // Verify
        System.out.println("\n" + "=".repeat(50));
        System.out.println("📋 VERIFICATION REPORT");
        System.out.println("=".repeat(50));

        List<Customer> allCustomers = bank.getAllCustomers();
        List<Account> allAccounts = bank.getAllAccounts();

        System.out.println("\nTotal Customers: " + allCustomers.size());
        System.out.println("Total Accounts: " + allAccounts.size());

        double totalBalance = 0;
        int savingsCount = 0, investmentCount = 0, chequeCount = 0;

        for (Account acc : allAccounts) {
            totalBalance += acc.getBalance();
            String type = acc.getAccountType();
            if (type.contains("Savings")) savingsCount++;
            else if (type.contains("Investment")) investmentCount++;
            else if (type.contains("Cheque")) chequeCount++;
        }

        System.out.println("\nAccount Breakdown:");
        System.out.println("   • Savings Accounts: " + savingsCount);
        System.out.println("   • Investment Accounts: " + investmentCount);
        System.out.println("   • Cheque Accounts: " + chequeCount);
        System.out.println("   • Total Balance: BWP " + String.format("%,.2f", totalBalance));

        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎉 SAMPLE DATA CREATION COMPLETE!");
        System.out.println("✅ 5 customers created");
        System.out.println("✅ 10 accounts created (meets assignment requirement)");
        System.out.println("✅ All data saved to H2 database");
        System.out.println("✅ Ready for final presentation");
        System.out.println("=".repeat(50));
    }
}