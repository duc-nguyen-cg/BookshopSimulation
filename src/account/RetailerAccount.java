package account;

import inputChecker.InputChecker;
import management.AccountManagement;
import management.ItemManagement;

public class RetailerAccount extends Account{
    private ItemManagement itemManager;
    private AccountManagement accountManager;


    public RetailerAccount(){}

    public RetailerAccount(ItemManagement itemManager, AccountManagement accountManager) {
        this.itemManager = itemManager;
        this.accountManager = accountManager;
    }


    public RetailerAccount(ItemManagement itemManager, AccountManagement accountManager, String accountName, String password) {
        super(accountName, password);
        this.itemManager = itemManager;
        this.accountManager = accountManager;
    }


    public RetailerAccount(ItemManagement itemManager, String accountName, String password) {
        super(accountName, password);
        this.itemManager = itemManager;
    }


    private void view(){
        itemManager.display();
    }

    private void add(){
        itemManager.add();
    }

    private void remove(){
        itemManager.removeByID();
    }

    private void edit(){
        itemManager.edit();
    }

    private void search(){
        itemManager.search();
    }

    private void sort(){
        itemManager.sort();
    }

    private void exportData(){
        itemManager.exportData();
    }

    private void importData(){
        itemManager.importData();
    }

    private void clearAll(){
        itemManager.clearAll();
    }

    private void checkProfit() {
        System.out.println("The current profit is "+ itemManager.getProfit()+" dollars");
    }


    @Override
    public void run(){
        if (login() == false){
            return;
        }

        int managerChoice;
        do {
            printMenu();
            managerChoice = InputChecker.inputIntegerInBounds(0, 12);
            switch (managerChoice) {
                case 1:
                    view(); break;
                case 2:
                    add(); break;
                case 3:
                    remove(); break;
                case 4:
                    edit(); break;
                case 5:
                    search(); break;
                case 6:
                    sort(); break;
                case 7:
                    exportData(); break;
                case 8:
                    importData(); break;
                case 9:
                    clearAll(); break;
                case 10:
                    checkProfit(); break;
                case 11:
                    changeAccountName(); break;
                case 12:
                    changePassWord(); break;
                case 0:
                    System.err.println(LOG_OUT_MESSAGE);
            }
        } while (managerChoice != 0);
    }


    public void printMenu(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\n  "+getAccountName()+"'s Account");
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
        System.out.println("11. Change account name");
        System.out.println("12. Change password");
        System.out.println("0. Log out");
        System.out.println("Enter retailer's choice: ");
    }

}
