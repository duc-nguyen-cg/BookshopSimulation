import account.Account;
import account.CustomerAccount;
import account.RetailerAccount;
import inputChecker.InputChecker;
import item_management.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    //print menus
    private static void printHomeScreen(){
        sleep();
        System.out.println("\nChoose your account: ");
        System.out.println("1. Retailer");
        System.out.println("2. Customer 1");
        System.out.println("3. Customer 2");
        System.out.println("0. End the program");
        System.out.println("Enter your choice: ");
    }

    private static void printLogInScreen(){
        sleep();
        System.out.println("\nLog-in Screen: ");
        System.out.println("1. Log in");
        System.out.println("0. Return");
        System.out.println("Enter your choice: ");
    }

    private static void sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
    }


    //run the program
    private static List<Account> init(){
        ItemManagement manager = ItemManagement.getInstance();
        Account retailer = new RetailerAccount(manager, "admin", "Erwin");
        Account customer1 = new CustomerAccount(manager, "jaeger", "Eren");
        Account customer2 = new CustomerAccount(manager, "ackerman", "Levi");

        List<Account> accounts = new ArrayList<>();
        accounts.add(retailer);
        accounts.add(customer1);
        accounts.add(customer2);
        return accounts;
    }

    private static void runAccount(List<Account> accounts, Account account) {
        printLogInScreen();
        int managerChoice = InputChecker.inputIntegerInBounds(0, 1);
        switch (managerChoice){
            case 1:
                account.run();
            case 0:
                runAll(accounts);
        }
    }

    private static void runAll(List<Account> accounts){
        int role;
        do {
            printHomeScreen();
            role = InputChecker.inputIntegerInBounds(0,accounts.size());
            if (role == 0){
                System.exit(0);
            }
            runAccount(accounts, accounts.get(role-1));
        } while (role != 0);
    }


    public static void main(String[] args) {
        List<Account> accounts = init();
        runAll(accounts);
    }
}
