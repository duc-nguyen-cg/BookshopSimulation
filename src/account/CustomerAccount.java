package account;

import basicClass.*;
import inputChecker.InputChecker;
import management.ItemManagement;
import java.util.*;


public class CustomerAccount extends Account{
    private ItemManagement manager;
    private List<Item> cart = new ArrayList<>();

    public CustomerAccount() {
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

        //enter amount to buy
        int number;
        do {
            System.out.println("Enter how many you want to buy: ");
            number = InputChecker.inputIntegerInBounds(1, left);
        } while (number > left);

        //add to cart, change the quantity
        foundItem.setQuantity( foundItem.getQuantity() - number);
        Item clone = foundItem.clone();
        clone.setQuantity(number);
        cart.add(clone);

        //pay
        manager.receivePayment(clone.getPrice() * clone.getQuantity());

    }


    public void run(){
        if (login() == false){
            return;
        }

        int userChoice;
        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 5);
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
                case 0:
                    System.err.println("The customer logged out!");
            }
        } while (userChoice != 0);
    }


    public void printMenu(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Interrupted!");
        }
        System.out.println("\nCustomer's Task:");
        System.out.println("1. Display items");
        System.out.println("2. Search");
        System.out.println("3. Sort");
        System.out.println("4. Buy");
        System.out.println("5. Show the cart");
        System.out.println("0. Log out");
        System.out.println("Enter user's choice: ");
    }
}
