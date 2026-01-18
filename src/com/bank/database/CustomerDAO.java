package com.bank.database;

import com.bank.model.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public int saveCustomer(Customer customer) {
        String sql = "INSERT INTO customers (first_name, surname, address) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getSurname());
            pstmt.setString(3, customer.getAddress());

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Save customer error: " + e.getMessage());
        }
        return -1;
    }

    public Customer getCustomer(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            System.err.println("Get customer error: " + e.getMessage());
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get all customers error: " + e.getMessage());
        }
        return customers;
    }
}