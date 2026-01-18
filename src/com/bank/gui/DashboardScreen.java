package com.bank.gui;

import com.bank.controller.LoginController;
import com.bank.controller.BankController;
import com.bank.model.Account;
import com.bank.model.Customer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class DashboardScreen {
    private LoginController loginController;
    private BankController bankController;

    public DashboardScreen(LoginController loginController, BankController bankController) {
        this.loginController = loginController;
        this.bankController = bankController;
    }

    public void show(Stage stage) {
        stage.setTitle("Banking Dashboard - Welcome " + loginController.getCurrentUser());

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #1A5276, #2E86C1);");

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-background-color: #2C3E50;");
        Menu fileMenu = new Menu("File");
        fileMenu.setStyle("-fx-text-fill: white;");
        MenuItem logoutItem = new MenuItem("Logout");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(logoutItem, new SeparatorMenuItem(), exitItem);
        menuBar.getMenus().add(fileMenu);

        // Side Menu
        VBox sideMenu = new VBox(10);
        sideMenu.setPadding(new Insets(15));
        sideMenu.setStyle("-fx-background-color: rgba(44, 62, 80, 0.9); -fx-background-radius: 10;");

        // Menu Buttons with fun emojis
        Button[] buttons = {
                createButton("👤 Create New Customer", "#E74C3C"),
                createButton("📋 See All Customers", "#3498DB"),
                createButton("💳 Open Bank Account", "#2ECC71"),
                createButton("💰 Deposit Cash", "#F39C12"),
                createButton("💸 Withdraw Money", "#9B59B6"),
                createButton("📊 View All Accounts", "#1ABC9C"),
                createButton("⚡ Quick Test", "#95A5A6"),
                createButton("🏦 Bank Stats", "#E67E22"),
                createButton("🚪 Exit Dashboard", "#7F8C8D")
        };

        for (Button btn : buttons) {
            sideMenu.getChildren().add(btn);
        }

        // Main Content Area
        TextArea contentArea = new TextArea();
        contentArea.setEditable(false);
        contentArea.setWrapText(true);
        contentArea.setPrefHeight(500);
        contentArea.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; " +
                "-fx-control-inner-background: #ECF0F1; -fx-text-fill: #2C3E50; " +
                "-fx-border-color: #3498DB; -fx-border-width: 2px; " +
                "-fx-border-radius: 5px;");
        contentArea.setText(
                "╔══════════════════════════════════════════════════════╗\n" +
                        "║                🎉 WELCOME TO YOUR BANK! 🎉           ║\n" +
                        "║                                                      ║\n" +
                        "║  Ready to build your banking empire?                ║\n" +
                        "║                                                      ║\n" +
                        "║  🌟 Choose from 3 account types:                    ║\n" +
                        "║     • 💰 Savings Account (Earns 0.05% interest)    ║\n" +
                        "║     • 📈 Investment Account (Earns 5% interest)    ║\n" +
                        "║     • 💼 Cheque Account (For working people only)  ║\n" +
                        "║                                                      ║\n" +
                        "║  💡 Important Rules:                               ║\n" +
                        "║     • Cheque accounts require employment proof      ║\n" +
                        "║     • Investment accounts need BWP 500 minimum      ║\n" +
                        "║     • Savings accounts don't allow withdrawals      ║\n" +
                        "║                                                      ║\n" +
                        "║  💡 Tip: Start with 'Create New Customer'           ║\n" +
                        "║     and watch your bank come to life!               ║\n" +
                        "╚══════════════════════════════════════════════════════╝\n\n" +
                        "🔹 Current Session: " + loginController.getCurrentUser() + "\n" +
                        "🔹 Ready to create something amazing!"
        );

        // ========== BUTTON ACTIONS ==========

        // 1. Create Customer
        buttons[0].setOnAction(e -> {
            TextInputDialog firstNameDialog = new TextInputDialog();
            firstNameDialog.setTitle("New Customer");
            firstNameDialog.setHeaderText("✨ Let's Add a New Customer!");
            firstNameDialog.setContentText("First Name:");

            firstNameDialog.showAndWait().ifPresent(firstName -> {
                TextInputDialog surnameDialog = new TextInputDialog();
                surnameDialog.setTitle("New Customer");
                surnameDialog.setHeaderText("✨ Let's Add a New Customer!");
                surnameDialog.setContentText("Surname:");

                surnameDialog.showAndWait().ifPresent(surname -> {
                    TextInputDialog addressDialog = new TextInputDialog();
                    addressDialog.setTitle("New Customer");
                    addressDialog.setHeaderText("✨ Let's Add a New Customer!");
                    addressDialog.setContentText("Address:");

                    addressDialog.showAndWait().ifPresent(address -> {
                        Customer newCustomer = bankController.createCustomer(firstName, surname, address);
                        if (newCustomer != null) {
                            contentArea.setText(
                                    "🎊 AMAZING! New Customer Added!\n\n" +
                                            "══════════════════════════════════════════\n" +
                                            "   🆔 Customer ID: " + newCustomer.getCustomerId() + "\n" +
                                            "   👤 Full Name: " + newCustomer.getFirstName() + " " + newCustomer.getSurname() + "\n" +
                                            "   📍 Address: " + newCustomer.getAddress() + "\n" +
                                            "   ⭐ Status: ACTIVE\n" +
                                            "══════════════════════════════════════════\n\n" +
                                            "🌟 Next Step: \n" +
                                            "   Open a bank account for " + newCustomer.getFirstName() + "\n" +
                                            "   Click '💳 Open Bank Account'"
                            );
                        } else {
                            contentArea.setText("😅 Oops! Let's try that again.\n" +
                                    "Make sure all fields are filled correctly.");
                        }
                    });
                });
            });
        });

        // 2. View Customers
        buttons[1].setOnAction(e -> {
            List<Customer> customers = bankController.getAllCustomers();
            if (customers.isEmpty()) {
                contentArea.setText(
                        "📭 No customers yet...\n\n" +
                                "Your bank is like an empty stage waiting for stars!\n" +
                                "🌟 Click 'Create New Customer' to add your first star!"
                );
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("🌟 YOUR BANKING STARS 🌟\n");
                sb.append("══════════════════════════════════════════\n\n");

                for (int i = 0; i < customers.size(); i++) {
                    Customer c = customers.get(i);
                    sb.append("[").append(i + 1).append("] ").append("✨\n");
                    sb.append("   Name: ").append(c.getFirstName()).append(" ").append(c.getSurname()).append("\n");
                    sb.append("   ID: ").append(c.getCustomerId()).append("\n");
                    sb.append("   Accounts: ").append(bankController.getCustomerAccounts(c.getCustomerId()).size()).append("\n");
                    sb.append("   ──────────────────────────\n");
                }

                sb.append("\n🎉 Total Stars: ").append(customers.size());
                if (customers.size() == 1) {
                    sb.append(" shining star!\n");
                } else {
                    sb.append(" shining stars!\n");
                }
                sb.append("🏆 Keep adding more!");

                contentArea.setText(sb.toString());
            }
        });

        // 3. Open Account - WITH WORKING STATUS CHECK!
        buttons[2].setOnAction(e -> {
            List<Customer> customers = bankController.getAllCustomers();
            if (customers.isEmpty()) {
                contentArea.setText(
                        "👥 Need customers first!\n\n" +
                                "You need customers before creating accounts.\n" +
                                "🌟 Click 'Create New Customer' to start!"
                );
                return;
            }

            // Step 1: Choose customer
            ChoiceDialog<Customer> customerDialog = new ChoiceDialog<>(customers.get(0), customers);
            customerDialog.setTitle("Choose Customer");
            customerDialog.setHeaderText("👤 Select a customer for the new account:");
            customerDialog.setContentText("Customer:");

            customerDialog.showAndWait().ifPresent(selectedCustomer -> {
                // Step 2: Choose account type
                ChoiceDialog<String> accountTypeDialog = new ChoiceDialog<>(
                        "Savings Account",
                        "Savings Account",
                        "Investment Account",
                        "Cheque Account"
                );
                accountTypeDialog.setTitle("Choose Account Type");
                accountTypeDialog.setHeaderText("💳 Select the type of account to open:");
                accountTypeDialog.setContentText("Account Type:");

                accountTypeDialog.showAndWait().ifPresent(accountType -> {
                    // Handle different account types
                    switch (accountType) {
                        case "Cheque Account":
                            createChequeAccount(selectedCustomer, contentArea);
                            break;
                        case "Savings Account":
                            createSavingsAccount(selectedCustomer, contentArea);
                            break;
                        case "Investment Account":
                            createInvestmentAccount(selectedCustomer, contentArea);
                            break;
                    }
                });
            });
        });

        // 4. Make Deposit
        buttons[3].setOnAction(e -> {
            List<Account> accounts = bankController.getAllAccounts();
            if (accounts.isEmpty()) {
                contentArea.setText(
                        "💳 No accounts yet!\n\n" +
                                "Create accounts first to make deposits.\n" +
                                "🌟 Click 'Open Bank Account' to start!"
                );
                return;
            }

            Account account = accounts.get(0);

            TextInputDialog depositDialog = new TextInputDialog("500");
            depositDialog.setTitle("Make Deposit");
            depositDialog.setHeaderText("💰 Adding Money to " + account.getAccountNumber());
            depositDialog.setContentText("Amount (BWP):");

            depositDialog.showAndWait().ifPresent(amountStr -> {
                try {
                    double amount = Double.parseDouble(amountStr);
                    boolean success = bankController.deposit(account.getAccountNumber(), amount);

                    if (success) {
                        Account updated = bankController.findAccount(account.getAccountNumber());
                        contentArea.setText(
                                "💵 DEPOSIT SUCCESS!\n\n" +
                                        "══════════════════════════════════════════\n" +
                                        "   🔢 Account: " + account.getAccountNumber() + "\n" +
                                        "   💰 Added: BWP " + amount + "\n" +
                                        "   📈 New Balance: BWP " + updated.getBalance() + "\n" +
                                        "   🎯 Transaction: COMPLETE\n" +
                                        "══════════════════════════════════════════\n\n" +
                                        "🌟 Your bank just grew richer!"
                        );
                    }
                } catch (NumberFormatException ex) {
                    contentArea.setText("🤔 Enter a valid number amount.");
                }
            });
        });

        // 5. Make Withdrawal
        buttons[4].setOnAction(e -> {
            List<Account> accounts = bankController.getAllAccounts();
            if (accounts.isEmpty()) {
                contentArea.setText("No accounts available for withdrawal.");
                return;
            }

            Account account = accounts.get(0);

            TextInputDialog withdrawDialog = new TextInputDialog("200");
            withdrawDialog.setTitle("Make Withdrawal");
            withdrawDialog.setHeaderText("💸 Taking Money from " + account.getAccountNumber());
            withdrawDialog.setContentText("Amount (BWP):");

            withdrawDialog.showAndWait().ifPresent(amountStr -> {
                try {
                    double amount = Double.parseDouble(amountStr);
                    boolean success = bankController.withdraw(account.getAccountNumber(), amount);

                    if (success) {
                        Account updated = bankController.findAccount(account.getAccountNumber());
                        contentArea.setText(
                                "💸 WITHDRAWAL SUCCESS!\n\n" +
                                        "══════════════════════════════════════════\n" +
                                        "   🔢 Account: " + account.getAccountNumber() + "\n" +
                                        "   💸 Withdrawn: BWP " + amount + "\n" +
                                        "   📉 New Balance: BWP " + updated.getBalance() + "\n" +
                                        "   🎯 Transaction: COMPLETE\n" +
                                        "══════════════════════════════════════════"
                        );
                    } else {
                        contentArea.setText(
                                "⚠️ Withdrawal Failed\n\n" +
                                        "Possible reasons:\n" +
                                        "• Not enough money in account\n" +
                                        "• Account type doesn't allow withdrawals\n" +
                                        "• Try a smaller amount"
                        );
                    }
                } catch (NumberFormatException ex) {
                    contentArea.setText("🤔 Enter a valid number amount.");
                }
            });
        });

        // 6. View Accounts
        buttons[5].setOnAction(e -> {
            List<Account> accounts = bankController.getAllAccounts();
            if (accounts.isEmpty()) {
                contentArea.setText(
                        "📭 No accounts yet...\n\n" +
                                "Your bank vault is empty!\n" +
                                "🌟 Open some accounts to fill it up!"
                );
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("💰 YOUR BANK ACCOUNTS 💰\n");
                sb.append("══════════════════════════════════════════\n\n");

                double total = 0;
                for (Account acc : accounts) {
                    sb.append("🔹 ").append(acc.getAccountNumber()).append("\n");
                    sb.append("   Type: ").append(acc.getAccountType()).append("\n");
                    sb.append("   Balance: BWP ").append(acc.getBalance()).append("\n");
                    sb.append("   Owner: ").append(acc.getCustomer().getFirstName()).append("\n");
                    sb.append("   ──────────────────────────\n");
                    total += acc.getBalance();
                }

                sb.append("\n📊 TOTAL BANK VALUE: BWP ").append(String.format("%,.2f", total)).append("\n");
                sb.append("🌟 Great work!");

                contentArea.setText(sb.toString());
            }
        });

        // 7. Quick Test
        buttons[6].setOnAction(e -> {
            contentArea.setText("⚡ QUICK SYSTEM CHECK\n\n");

            contentArea.appendText("Running diagnostics...\n\n");

            // Create test customer
            Customer testCustomer = bankController.createCustomer("Quick", "Test", "123 Check Street");
            if (testCustomer != null) {
                contentArea.appendText("✅ Customer system: WORKING\n");

                // Create test account
                Account testAccount = bankController.openSavingsAccount(
                        testCustomer.getCustomerId(), 1500.0, "Test Branch");
                if (testAccount != null) {
                    contentArea.appendText("✅ Account system: WORKING\n");

                    // Test deposit
                    boolean depositSuccess = bankController.deposit(testAccount.getAccountNumber(), 300.0);
                    contentArea.appendText("✅ Transaction system: " + (depositSuccess ? "WORKING" : "OK") + "\n");
                }
            }

            contentArea.appendText("\n📊 YOUR CURRENT BANK:\n");
            contentArea.appendText("   👥 Customers: " + bankController.getAllCustomers().size() + "\n");
            contentArea.appendText("   💳 Accounts: " + bankController.getAllAccounts().size() + "\n\n");

            contentArea.appendText("🎉 Everything looks great! Ready to continue!");
        });

        // 8. Bank Stats
        buttons[7].setOnAction(e -> {
            contentArea.setText(bankController.getBankSummary());
        });

        // 9. Logout
        buttons[8].setOnAction(e -> {
            loginController.logout();
            LoginScreen login = new LoginScreen();
            login.show(stage);
        });

        logoutItem.setOnAction(e -> {
            loginController.logout();
            LoginScreen login = new LoginScreen();
            login.show(stage);
        });

        exitItem.setOnAction(e -> stage.close());

        // Layout
        root.setTop(menuBar);
        root.setLeft(sideMenu);
        root.setCenter(contentArea);
        BorderPane.setMargin(contentArea, new Insets(10));

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
    }

    // ========== HELPER METHODS FOR ACCOUNT CREATION ==========

    private void createChequeAccount(Customer customer, TextArea contentArea) {
        // First ask: Is this person working?
        Alert workingAlert = new Alert(Alert.AlertType.CONFIRMATION);
        workingAlert.setTitle("Employment Check");
        workingAlert.setHeaderText("💼 Cheque Account Requirements");
        workingAlert.setContentText("Is " + customer.getFirstName() + " " + customer.getSurname() + " currently working?\n" +
                "Cheque accounts are ONLY for employed people.");

        workingAlert.showAndWait().ifPresent(response -> {
            if (response.getButtonData().isDefaultButton()) {
                // User clicked OK (Yes, they are working)

                // Ask for employer information
                TextInputDialog employerDialog = new TextInputDialog();
                employerDialog.setTitle("Employer Information");
                employerDialog.setHeaderText("🏢 Please provide employment details for " + customer.getFirstName());
                employerDialog.setContentText("Company/Employer Name:");

                employerDialog.showAndWait().ifPresent(employerName -> {
                    TextInputDialog addressDialog = new TextInputDialog();
                    addressDialog.setTitle("Employer Information");
                    addressDialog.setHeaderText("🏢 Please provide employment details for " + customer.getFirstName());
                    addressDialog.setContentText("Company Address:");

                    addressDialog.showAndWait().ifPresent(employerAddress -> {
                        // Now ask for initial deposit
                        TextInputDialog amountDialog = new TextInputDialog("1000");
                        amountDialog.setTitle("Initial Deposit");
                        amountDialog.setHeaderText("💰 Opening Cheque Account for " + customer.getFirstName());
                        amountDialog.setContentText("Initial deposit amount (BWP):");

                        amountDialog.showAndWait().ifPresent(amountStr -> {
                            try {
                                double amount = Double.parseDouble(amountStr);
                                Account newAccount = bankController.openChequeAccount(
                                        customer.getCustomerId(), amount, "Main Branch",
                                        employerName, employerAddress);

                                if (newAccount != null) {
                                    contentArea.setText(
                                            "💼 CHEQUE ACCOUNT CREATED!\n\n" +
                                                    "══════════════════════════════════════════\n" +
                                                    "   🔢 Account #: " + newAccount.getAccountNumber() + "\n" +
                                                    "   📋 Type: Cheque Account (Salary Account)\n" +
                                                    "   👤 Owner: " + customer.getFirstName() + " " + customer.getSurname() + "\n" +
                                                    "   💰 Balance: BWP " + newAccount.getBalance() + "\n" +
                                                    "   🏢 Branch: Main Branch\n" +
                                                    "   💼 Employer: " + employerName + "\n" +
                                                    "   📍 Company Address: " + employerAddress + "\n" +
                                                    "══════════════════════════════════════════\n\n" +
                                                    "✅ Perfect for salary payments!\n" +
                                                    "💡 No interest earned on cheque accounts."
                                    );
                                }
                            } catch (NumberFormatException ex) {
                                contentArea.setText("🤔 Enter a valid amount like 1000 or 500.50");
                            }
                        });
                    });
                });
            } else {
                // User clicked Cancel (No, they are not working)
                contentArea.setText(
                        "⚠️ CANNOT OPEN CHEQUE ACCOUNT\n\n" +
                                "Cheque accounts are only available for employed individuals.\n" +
                                customer.getFirstName() + " needs to be working to open this account.\n\n" +
                                "💡 Try opening a Savings or Investment account instead!"
                );
            }
        });
    }

    private void createSavingsAccount(Customer customer, TextArea contentArea) {
        TextInputDialog amountDialog = new TextInputDialog("500");
        amountDialog.setTitle("Open Savings Account");
        amountDialog.setHeaderText("💰 Opening Savings Account for " + customer.getFirstName());
        amountDialog.setContentText("Savings Account Details:\n" +
                "• Earns 0.05% monthly interest\n" +
                "• No withdrawals allowed\n" +
                "• Safe for future savings\n\n" +
                "Initial deposit (BWP):");

        amountDialog.showAndWait().ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);
                Account newAccount = bankController.openSavingsAccount(
                        customer.getCustomerId(), amount, "Main Branch");

                if (newAccount != null) {
                    contentArea.setText(
                            "💰 SAVINGS ACCOUNT CREATED!\n\n" +
                                    "══════════════════════════════════════════\n" +
                                    "   🔢 Account #: " + newAccount.getAccountNumber() + "\n" +
                                    "   📋 Type: Savings Account\n" +
                                    "   👤 Owner: " + customer.getFirstName() + " " + customer.getSurname() + "\n" +
                                    "   💰 Balance: BWP " + newAccount.getBalance() + "\n" +
                                    "   🏢 Branch: Main Branch\n" +
                                    "   ⚡ Interest: 0.05% monthly\n" +
                                    "   ⚠️ Withdrawals: NOT ALLOWED\n" +
                                    "══════════════════════════════════════════\n\n" +
                                    "🌟 Perfect for safe savings!\n" +
                                    "💡 Your money will earn interest every month."
                    );
                }
            } catch (NumberFormatException ex) {
                contentArea.setText("🤔 Enter a valid amount like 500 or 1000.50");
            }
        });
    }

    private void createInvestmentAccount(Customer customer, TextArea contentArea) {
        TextInputDialog amountDialog = new TextInputDialog("500");
        amountDialog.setTitle("Open Investment Account");
        amountDialog.setHeaderText("📈 Opening Investment Account for " + customer.getFirstName());
        amountDialog.setContentText("Investment Account Details:\n" +
                "• Earns 5% monthly interest\n" +
                "• Minimum BWP 500 required\n" +
                "• Allows withdrawals\n" +
                "• Higher returns than savings\n\n" +
                "Initial deposit (BWP):");

        amountDialog.showAndWait().ifPresent(amountStr -> {
            try {
                double amount = Double.parseDouble(amountStr);

                // Check minimum balance
                if (amount < 500) {
                    contentArea.setText(
                            "⚠️ MINIMUM DEPOSIT REQUIRED\n\n" +
                                    "Investment accounts require minimum BWP 500.00\n" +
                                    "You entered: BWP " + amount + "\n\n" +
                                    "💡 Please deposit at least BWP 500 to open an investment account."
                    );
                    return;
                }

                Account newAccount = bankController.openInvestmentAccount(
                        customer.getCustomerId(), amount, "Main Branch");

                if (newAccount != null) {
                    contentArea.setText(
                            "📈 INVESTMENT ACCOUNT CREATED!\n\n" +
                                    "   🔢 Account #: " + newAccount.getAccountNumber() + "\n" +
                                    "   📋 Type: Investment Account\n" +
                                    "   👤 Owner: " + customer.getFirstName() + " " + customer.getSurname() + "\n" +
                                    "   💰 Balance: BWP " + newAccount.getBalance() + "\n" +
                                    "   🏢 Branch: Main Branch\n" +
                                    "   ⚡ Interest: 5% monthly (High returns!)\n" +
                                    "   💸 Withdrawals: ALLOWED\n" +
                                    "   ⭐ Minimum: BWP 500 maintained\n" +
                                    "🌟 Great choice for growing your money!\n" +
                                    "💡 Your investment will earn high interest monthly."
                    );
                }
            } catch (NumberFormatException ex) {
                contentArea.setText("🤔 Enter a valid amount like 500 or 1000.50");
            }
        });
    }

    private Button createButton(String text, String color) {
        Button btn = new Button(text);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setPrefHeight(45);
        btn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; " +
                "-fx-background-color: " + color + "; " +
                "-fx-text-fill: white; -fx-border-radius: 8px; " +
                "-fx-alignment: center-left; -fx-padding: 10px 15px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);");

        // Hover effect
        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: derive(" + color + ", 30%); " +
                        "-fx-text-fill: white; -fx-border-radius: 8px; " +
                        "-fx-alignment: center-left; -fx-padding: 10px 15px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3);")
        );
        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; " +
                        "-fx-background-color: " + color + "; " +
                        "-fx-text-fill: white; -fx-border-radius: 8px; " +
                        "-fx-alignment: center-left; -fx-padding: 10px 15px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 5, 0, 0, 2);")
        );

        return btn;
    }
}