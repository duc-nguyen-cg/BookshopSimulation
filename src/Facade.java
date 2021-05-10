import inputChecker.InputChecker;
import management.*;

public class Facade {
    public static void manage() {
        ItemManagement manager = ItemManagement.getInstance();

        int managerChoice;
        do {
            printManagerMenu();
            managerChoice = InputChecker.inputIntegerInBounds(0, 9);
            switch (managerChoice){
                case 1:
                    manager.display(); break;
                case 2:
                    manager.add(); break;
                case 3:
                    manager.removeByID(); break;
                case 4:
                    manager.edit(); break;
                case 5:
                    manager.search(); break;
                case 6:
                    manager.sort(); break;
                case 7:
                    manager.exportData(); break;
                case 8:
                    manager.importData(); break;
                case 9:
                    manager.clearAll(); break;
                case 0:
                    run();
            }
        } while (managerChoice != 0);
    }


    public static void printManagerMenu(){
        sleep();
        System.out.println("\nManager's Task: ");
        System.out.println("1. Display items");
        System.out.println("2. Add");
        System.out.println("3. Remove");
        System.out.println("4. Edit");
        System.out.println("5. Search");
        System.out.println("6. Sort");
        System.out.println("7. Export data");
        System.out.println("8. Import data");
        System.out.println("9. Clear all data");
        System.out.println("0. Exit");
        System.out.println("Enter manager's choice: ");
    }


    public static void use(){
        ItemManagement user = ItemManagement.getInstance();
        int userChoice;
        do {
            printUserMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 4);
            switch (userChoice){
                case 1:
                    user.display(); break;
                case 2:
                    user.search(); break;
                case 3:
                    user.sort(); break;
                case 4:
                    user.exportData(); break;
                case 0:
                    run();
            }
        } while (userChoice != 0);
    }


    public static void printUserMenu(){
        sleep();
        System.out.println("\nUser's Task:");
        System.out.println("1. Display items");
        System.out.println("2. Search");
        System.out.println("3. Sort"); //need to fix
        System.out.println("4. Export data");
        System.out.println("0. Exit");
        System.out.println("Enter user's choice: ");
    }


    public static void printFirstScreen(){
        sleep();
        System.out.println("\nChoose your role: ");
        System.out.println("1. Manager");
        System.out.println("2. User");
        System.out.println("0. End the program");
        System.out.println("Enter your choice: ");
    }


    public static void run(){
        int role;
        do {
            printFirstScreen();
            role = InputChecker.inputIntegerInBounds(0,2);
            switch (role){
                case 1:
                    manage();
                case 2:
                    use();
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
        Facade.run();
    }
}
