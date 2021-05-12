import account.CustomerAccount;
import account.RetailerAccount;
import inputChecker.InputChecker;
import management.*;

public class Main {
    private static void runAccount(RetailerAccount retailer) {
        printLogInScreen();
        int managerChoice = InputChecker.inputIntegerInBounds(0, 1);
        switch (managerChoice){
            case 1:
                retailer.run();
            case 0:
                run();
            }
    }


    private static void runAccount(CustomerAccount customer){
        printLogInScreen();
        int customerChoice = InputChecker.inputIntegerInBounds(0,1);
        switch (customerChoice){
            case 1:
                customer.run();
            case 0:
                run();
        }
    }


    private static void printHomeScreen(){
        sleep();
        System.out.println("\nChoose your role: ");
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
    }

    private static void run(){
        ItemManagement manager = ItemManagement.getInstance();
        RetailerAccount retailer = new RetailerAccount(manager);
        CustomerAccount customer1 = new CustomerAccount(manager);
        CustomerAccount customer2 = new CustomerAccount(manager);

        int role;
        do {
            printHomeScreen();
            role = InputChecker.inputIntegerInBounds(0,3);
            switch (role){
                case 1:
                    runAccount(retailer);
                case 2:
                    runAccount(customer1);
                case 3:
                    runAccount(customer2);
                case 0:
                    System.exit(0);
            }
        } while (role != 0);
    }


    private static void sleep(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
    }


    public static void main(String[] args) {
        Main.run();
    }
}
