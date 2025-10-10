package com.bank.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    private final String customerId;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String email;
    private final String password;
    private final List<Account> accounts = new ArrayList<>();

    public Customer(String firstName, String lastName, String address, String email, String password) {
        this.customerId = IdGenerator.nextId("C-");
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public List<Account> getAccounts() { return Collections.unmodifiableList(accounts); }

    public void addAccount(Account acc) { accounts.add(acc); }

    @Override
    public String toString() {
        return "Customer " + firstName + " " + lastName + " (" + customerId + ")";
    }
}
