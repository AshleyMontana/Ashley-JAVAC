package com.bank.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String firstName;
    private String surname;
    private String address;
    private List<Account> accounts;

    public Customer(int customerId, String firstName, String surname, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public int getCustomerId() { return customerId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<Account> getAccounts() { return accounts; }

    @Override
    public String toString() {
        return "Customer: " + firstName + " " + surname + " (ID: " + customerId + ")";
    }
}