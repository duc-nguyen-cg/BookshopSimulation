import inputChecker.InputChecker;
import manager.*;

public class Main {
    public static void main(String[] args) {
        //init manager and add observers
        ItemManagement itemManager = ItemManagement.getInstance();
        new BookList(itemManager);
        new MagazineList(itemManager);
        new NewspaperList(itemManager);
        new StationeryList(itemManager);

        //run
        int userChoice;
        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 9);
            switch (userChoice){
                case 1:
                    itemManager.display(); break;
                case 2:
                    itemManager.add(); break;
                case 3:
                    itemManager.removeByID(); break;
                case 4:
                    itemManager.edit(); break;
                case 5:
                    itemManager.search(); break;
                case 6:
                    itemManager.sort(); break;
                case 7:
                    itemManager.exportData(); break;
                case 8:
                    itemManager.importData(); break;
                case 9:
                    itemManager.clearAll(); break;
                case 0:
                    System.exit(0);
            }
        } while (userChoice != 0);
    }


    public static void printMenu(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\nMenu: ");
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
        System.out.println("Enter your choice: ");
    }
}
