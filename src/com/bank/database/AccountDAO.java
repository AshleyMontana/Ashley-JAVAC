package com.bank.database;

import com.bank.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public boolean saveAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, customer_id, account_type, balance, branch, employer_name, employer_address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountNumber());
            pstmt.setInt(2, account.getCustomer().getCustomerId());
            pstmt.setString(3, account.getClass().getSimpleName());
            pstmt.setDouble(4, account.getBalance());
            pstmt.setString(5, account.getBranch());

            if (account instanceof ChequeAccount) {
                ChequeAccount ca = (ChequeAccount) account;
                pstmt.setString(6, ca.getEmployerName());
                pstmt.setString(7, ca.getEmployerAddress());
            } else {
                pstmt.setString(6, null);
                pstmt.setString(7, null);
            }

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Save account error: " + e.getMessage());
        }
        return false;
    }

    public Account getAccount(String accountNumber) {
        String sql = "SELECT a.*, c.first_name, c.surname, c.address " +
                "FROM accounts a JOIN customers c ON a.customer_id = c.id " +
                "WHERE a.account_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address")
                );

                String type = rs.getString("account_type");
                double balance = rs.getDouble("balance");
                String branch = rs.getString("branch");

                switch (type) {
                    case "SavingsAccount":
                        return new SavingsAccount(accountNumber, balance, customer, branch);
                    case "InvestmentAccount":
                        return new InvestmentAccount(accountNumber, balance, customer, branch);
                    case "ChequeAccount":
                        return new ChequeAccount(accountNumber, balance, customer, branch,
                                rs.getString("employer_name"),
                                rs.getString("employer_address"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Get account error: " + e.getMessage());
        }
        return null;
    }

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT a.*, c.first_name, c.surname, c.address " +
                "FROM accounts a JOIN customers c ON a.customer_id = c.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address")
                );

                String accNo = rs.getString("account_number");
                String type = rs.getString("account_type");
                double balance = rs.getDouble("balance");
                String branch = rs.getString("branch");

                Account account;
                switch (type) {
                    case "SavingsAccount":
                        account = new SavingsAccount(accNo, balance, customer, branch);
                        break;
                    case "InvestmentAccount":
                        account = new InvestmentAccount(accNo, balance, customer, branch);
                        break;
                    case "ChequeAccount":
                        account = new ChequeAccount(accNo, balance, customer, branch,
                                rs.getString("employer_name"),
                                rs.getString("employer_address"));
                        break;
                    default:
                        continue;
                }
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.err.println("Get all accounts error: " + e.getMessage());
        }
        return accounts;
    }

    public boolean updateBalance(String accountNumber, double newBalance) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update balance error: " + e.getMessage());
        }
        return false;
    }
}