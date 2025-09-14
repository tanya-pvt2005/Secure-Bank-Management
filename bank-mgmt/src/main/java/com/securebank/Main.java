package com.securebank;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.securebank.config.AppConfig;
import com.securebank.controller.AccountController;
import com.securebank.controller.UserController;

public class Main {

    public static void main(String[] args) {

    	
    	//start spring
    	AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(AppConfig.class);
        
    	//get userController bean
    	UserController controller = context.getBean(UserController.class);
    	AccountController accountController = context.getBean(AccountController.class);

    	Scanner sc = new Scanner(System.in);
        String adminUser = "admin";   // hardcoded admin username
        String adminPass = "##Admin"; // hardcoded admin password

        System.out.println("=== Welcome to Bank Management App ===");

        System.out.print("Enter admin username: ");
        String username = sc.nextLine();

        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        if (!adminUser.equals(username) || !adminPass.equals(password)) {
            System.out.println("Invalid credentials. Exiting...");
            sc.close();
            return;
        }

        System.out.println("Login successful!\n");

        boolean exit = false;

        while (!exit) {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Create Account for a User");
            System.out.println("5. Deposit/Withdraw for a User");
            System.out.println("6. View account history for user");
            System.out.println("7. View all Users");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
 //-----------------------------------------------------------------------------------------------------------------
                case 1:
                    System.out.println("You selected: Create User");
                    
                    System.out.print("Enter User ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    
                    System.out.print("Enter first name: ");
                    String fName = sc.nextLine();

                    System.out.print("Enter last name: ");
                    String lName = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    controller.createUser(id, fName, lName, email);
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 2:
                    System.out.println("You selected: Update User");
                    System.out.print("Enter User ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New First Name: ");
                    String newFName = sc.nextLine();
                    System.out.print("New Last Name: ");
                    String newLName = sc.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = sc.nextLine();

                    controller.updateUser(updateId, newFName, newLName, newEmail);
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 3:
                    System.out.println("You selected: Delete User");
                    System.out.print("Enter User ID: ");
                    int deleteId = sc.nextInt();
                    sc.nextLine();
                    controller.deleteUser(deleteId);
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 4:
                    System.out.println("You selected: Create Account for User");
                    System.out.print("Enter User ID: ");
                    long userId = sc.nextInt();
                    sc.nextLine(); // consume newline
                    
                    System.out.print("Enter Account Type (Savings/Current): ");
                    String accountType = sc.nextLine();

                    System.out.print("Enter Initial Balance: ");
                    double initialBalance = sc.nextDouble();
                    sc.nextLine(); // consume newline

                    accountController.createAccount(userId, accountType, initialBalance);
            
                    break;

//-----------------------------------------------------------------------------------------------------------------
                case 5:
                                     
                    System.out.println("You selected: Make Transaction");
                    System.out.print("Enter Account ID: ");
                    long accountId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("1. Deposit");
                    System.out.println("2. Withdraw");
                    System.out.print("Select transaction type: ");
                    int txnChoice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    if (txnChoice == 1) {
                        accountController.deposit(accountId, amount);
                        System.out.println("Deposit successful.");
                    } else if (txnChoice == 2) {
                        accountController.withdraw(accountId, amount);
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Invalid transaction choice.");
                    }
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 6:
                    System.out.println("You selected: View account history for user");
              
                    System.out.print("Enter User ID: ");
                    int uId = sc.nextInt();
                    sc.nextLine(); // consume newline

                    // Show user details
                    controller.getUserById(uId);
                    accountController.viewAccounts((long) uId);
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 7:
                    System.out.println("You selected: View all Users");
                    System.out.println();
                    controller.viewAllUsers();
                    break;
//-----------------------------------------------------------------------------------------------------------------

                case 8:
                    System.out.println("Exiting... Goodbye!");
                    exit = true;
                    break;
//-----------------------------------------------------------------------------------------------------------------

                default:
                    System.out.println("Invalid choice. Try again.");
            }
            System.out.println(); // blank line for readability
        }

        sc.close();
    }
}
