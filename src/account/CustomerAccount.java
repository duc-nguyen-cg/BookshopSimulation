package account;

import basicClass.*;
import inputChecker.InputChecker;
import item_management.ItemManagement;
import java.util.*;


public class CustomerAccount extends Account{
    private static final Scanner scanner = new Scanner(System.in);
    private static final double MAX_MONEY = 500;

    private ItemManagement manager;
    private List<Item> cart = new ArrayList<>();
    private double wallet = MAX_MONEY;


    public CustomerAccount(ItemManagement manager) {
        this.manager = manager;
    }

    public CustomerAccount(ItemManagement manager, String accountName, String password) {
        super(accountName, password);
        this.manager = manager;
    }

    private void showCart(){

        if (cart.isEmpty()){
            System.err.println("Empty!");
        } else {
            System.out.println("Cart: ");
            for (Item item: cart){
                System.out.println(item);
            }
        }
    }


    private void view(){
        manager.display();
    }


    private void sort(){
        manager.sort();
    }


    private void search(){
        manager.search();
    }


    private void buy(){

        //find and check quantity left
        List<Item> found = manager.searchByID();
        Item foundItem = found.get(0);
        if (foundItem == null ){
            System.err.println("Not found!");
            return;
        }
        int left = found.get(0).getQuantity();
        if (left == 0){
            System.err.println("Not available!");
            return;
        }
        System.out.println(foundItem);

        //enter amount to buy
        int number;
        do {
            do {
                System.out.println("Enter how many you want to buy: ");
                number = InputChecker.inputIntegerInBounds(1, Integer.MAX_VALUE);

            } while (number > left);
            if (wallet < foundItem.getPrice() * number){
                System.err.println("Not enough money!");
            }
        } while (wallet < foundItem.getPrice() * number);


        //add to cart, change the quantity
        foundItem.setQuantity( foundItem.getQuantity() - number);
        Item clone = foundItem.clone();
        clone.setQuantity(number);
        cart.add(clone);

        //pay and inform
        manager.receivePayment(clone.getPrice() * clone.getQuantity());
        wallet -= clone.getPrice() * clone.getQuantity();
        System.out.println("Exchange successfully!");

    }


    private void checkAccountBalance(){
        System.out.printf("You have %.2f dollar(s)\n", wallet);
    }

    private void recharge(){
        System.out.println("How much money you want to recharge: ");
        double charged = InputChecker.inputDoubleInBounds(0.1, Double.MAX_VALUE);
        if (wallet + charged > MAX_MONEY){
            wallet = MAX_MONEY;
        } else {
            wallet += charged;
        }
        checkAccountBalance();
    }


    @Override
    public void run(){
        if (login() == false){
            return;
        }

        int userChoice;
        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 9);
            switch (userChoice){
                case 1:
                    view(); break;
                case 2:
                    search(); break;
                case 3:
                    sort(); break;
                case 4:
                    buy(); break;
                case 5:
                    showCart(); break;
                case 6:
                    checkAccountBalance(); break;
                case 7:
                    recharge(); break;
                case 8:
                    changeAccountName(); break;
                case 9:
                    changePassWord(); break;
                case 0:
                    System.err.println(LOG_OUT_MESSAGE);
            }
        } while (userChoice != 0);
    }


    public void printMenu(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\n  "+getAccountName()+"'s Account");
        System.out.println("1. Display items");
        System.out.println("2. Search");
        System.out.println("3. Sort");
        System.out.println("4. Buy");
        System.out.println("5. Show the cart");
        System.out.println("6. Check account balance");
        System.out.println("7. Recharge");
        System.out.println("8. Change account name");
        System.out.println("9. Change password");
        System.out.println("0. Log out");
        System.out.println("Enter customer's choice: ");
    }
}
