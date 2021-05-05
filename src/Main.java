import item.InputChecker;

public class Main {
    public static void main(String[] args) {
        ItemManagement bookshop = ItemManagement.getInstance();
        int userChoice;

        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 2);
            switch (userChoice){
                case 1:
                    bookshop.display();
                    break;
                case 2:
                    bookshop.add();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Invalid task, please enter your choice again: ");
                    break;
            }
        } while (userChoice != 0);
    }


    public static void printMenu(){
        System.out.println("Menu: ");
        System.out.println("1. Display all items");
        System.out.println("2. Add a new item");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }
}
