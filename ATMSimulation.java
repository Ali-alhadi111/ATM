import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSimulation {
    private Map<String, Account> accounts;
    private Scanner scanner;
    private Account currentAccount;
    
    // Constructor
    public ATMSimulation() {
        accounts = new HashMap<>();
        scanner = new Scanner(System.in);
        initializeAccounts();
    }
    
    // Initialize sample accounts for testing
    private void initializeAccounts() {
        accounts.put("1234", new Account("1234", "John Doe", 1500.00));
        accounts.put("5678", new Account("5678", "Jane Smith", 2500.75));
        accounts.put("9999", new Account("9999", "Bob Johnson", 500.25));
    }
    
    // Main method to run the ATM simulation
    public static void main(String[] args) {
        ATMSimulation atm = new ATMSimulation();
        atm.run();
    }
    
    // Main ATM loop
    public void run() {
        System.out.println("=== Welcome to Simple ATM Simulation ===");
        
        while (true) {
            if (currentAccount == null) {
                if (!login()) {
                    continue;
                }
            }
            
            displayMenu();
            int choice = getChoice();
            
            switch (choice) {
                case 1:
                    displayBalance();
                    break;
                case 2:
                    logout();
                    break;
                case 3:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    // PIN validation and login
    private boolean login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        
        if (accounts.containsKey(pin)) {
            currentAccount = accounts.get(pin);
            System.out.println("Welcome, " + currentAccount.getAccountHolder() + "!");
            return true;
        } else {
            System.out.println("Invalid PIN. Please try again.");
            return false;
        }
    }
    
    // Display main menu
    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Logout");
        System.out.println("3. Exit");
        System.out.print("Please select an option (1-3): ");
    }
    
    // Get user choice with input validation
    private int getChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            return -1; // Invalid choice
        }
    }
    
    // Display account balance
    private void displayBalance() {
        System.out.println("\n--- Account Balance ---");
        System.out.println("Account Holder: " + currentAccount.getAccountHolder());
        System.out.println("Account Number: " + currentAccount.getAccountNumber());
        System.out.printf("Current Balance: $%.2f%n", currentAccount.getBalance());
    }
    
    // Logout current user
    private void logout() {
        System.out.println("Logging out...");
        currentAccount = null;
        System.out.println("You have been logged out successfully.");
    }
}

// Account class to store account information
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
    
    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    // Setters (for future functionality)
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
