package account;

import inputChecker.InputChecker;
import management.ItemManagement;

public class RetailerAccount {
    private ItemManagement manager;
    private String accountName;
    private String password;

    public RetailerAccount() {
    }

    public RetailerAccount(ItemManagement manager) {
        this.manager = manager;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private void view(){
        manager.display();
    }


    private void add(){
        manager.add();
    }


    private void remove(){
        manager.removeByID();
    }

    private void edit(){
        manager.edit();
    }

    private void search(){
        manager.search();
    }


    private void sort(){
        manager.sort();
    }


    private void exportData(){
        manager.exportData();
    }


    private void importData(){
        manager.importData();
    }


    private void clearAll(){
        manager.clearAll();
    }


    private void checkProfit() {
        System.out.println("The current profit is "+manager.getProfit()+" dollars");
    }

    public void run(){
        int managerChoice;
        do {
            printMenu();
            managerChoice = InputChecker.inputIntegerInBounds(0, 10);
            switch (managerChoice) {
                case 1:
                    view();
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    remove();
                    break;
                case 4:
                    edit();
                    break;
                case 5:
                    search();
                    break;
                case 6:
                    sort();
                    break;
                case 7:
                    exportData();
                    break;
                case 8:
                    importData();
                    break;
                case 9:
                    clearAll();
                    break;
                case 10:
                    checkProfit();
                    break;
                case 0:
                    System.err.println("The retailer logged out!");
            }
        } while (managerChoice != 0);
    }


    public static void printMenu(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\nRetailer's Task: ");
        System.out.println("1. Display items");
        System.out.println("2. Add");
        System.out.println("3. Remove");
        System.out.println("4. Edit");
        System.out.println("5. Search");
        System.out.println("6. Sort");
        System.out.println("7. Export data");
        System.out.println("8. Import data");
        System.out.println("9. Clear all data");
        System.out.println("10. Check the profit");
        System.out.println("0. Log out");
        System.out.println("Enter manager's choice: ");
    }
}
