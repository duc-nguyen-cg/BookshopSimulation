import item.InputChecker;

public class Main {
    public static void main(String[] args) {
        ItemManagement itemManager = ItemManagement.getInstance();
        int userChoice;

        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 7);
            switch (userChoice){
                case 1:
                    itemManager.display();
                    break;
                case 2:
                    itemManager.add();
                    break;
                case 3:
                    itemManager.removeByID();
                    break;
                case 4:
                    itemManager.searchByID();
                    break;
                case 5:
                    itemManager.sort();
                    break;
                case 6:
                    itemManager.exportData();
                    break;
                case 7:
                    itemManager.importData();
                    break;
                case 0:
                    System.exit(0);
            }
        } while (userChoice != 0);
    }


    public static void printMenu(){
        System.out.println("\nMenu: ");
        System.out.println("1. Display all items");
        System.out.println("2. Add a new item");
        System.out.println("3. Remove an item");
        System.out.println("4. Search an item by ID");
        System.out.println("5. Sort");
        System.out.println("6. Export data");
        System.out.println("7. Import data");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }
}
