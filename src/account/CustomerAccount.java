package account;

import basicClass.*;
import inputChecker.InputChecker;
import management.ItemManagement;
import java.util.*;


public class CustomerAccount extends Account{
    private static final double MAX_MONEY = 500;
    private static final int MAX_LOYAL_LEVEl = 5, MIN_LOYAL_LEVEL = 1;

    private ItemManagement manager;
    private Stack<Item> cart = new Stack<>();
    private double wallet = MAX_MONEY;
    private double totalPaid = 0;

    public CustomerAccount(){}

    public CustomerAccount(ItemManagement manager) {
        this.manager = manager;
    }

    public CustomerAccount(ItemManagement manager, String accountName, String password) {
        super(accountName, password);
        this.manager = manager;
    }


    private double getTotalPaid(){ return totalPaid; }


    public String getLoyalLevel(){
        int rawLevel = (int) (totalPaid /MAX_MONEY) ;
        if (rawLevel > MAX_LOYAL_LEVEl){
            rawLevel = MAX_LOYAL_LEVEl;
        } else if (rawLevel < MIN_LOYAL_LEVEL){
            rawLevel = MIN_LOYAL_LEVEL;
        }

        String result = "";
        for (int i = 0; i < rawLevel; i++){
            result += "*";
        }
        return result;
    }


    public double getDiscount(){
        String currentLoyalLevel = getLoyalLevel();
        return (currentLoyalLevel.length()-1)*5;
    }


    private void printStack(Stack<Item> stack){
        if (stack.isEmpty()){
            return;
        } else {
            Item item = stack.pop();
            System.out.println(item);
            printStack(stack);
            stack.push(item);
        }
    }


    private void showCart(){
        System.out.println("Cart: ");
        printStack(cart);
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

        //find and print item info
        List<Item> found = manager.searchByID();
        Item foundItem = found.get(0);
        if (foundItem == null ){
            System.err.println("Not found!");
            return;
        }
        System.out.println(foundItem);
        if (getDiscount() != 0){
            System.out.println(+getDiscount()+"% discount for every item");
        }
        double discountPrice = foundItem.getPrice()*(1-getDiscount()/100);

        //check wallet
        checkAccountBalance();
        if (wallet < discountPrice){
            System.err.println("Not enough money!");
            return;
        }

        //enter amount to buy
        int left = foundItem.getQuantity();
        int number;
        do {
            do {
                System.out.println("Enter how many you want to buy: ");
                number = InputChecker.inputIntegerInBounds(1, left);
            } while (number > left);
            if (wallet < discountPrice * number){
                System.err.println("Not enough money!");
            }
        } while (wallet < discountPrice * number);


        //add to cart, change the quantity
        foundItem.setQuantity( foundItem.getQuantity() - number);
        Item clone = foundItem.clone();
        clone.setQuantity(number);
        cart.add(clone);

        //pay and inform
        double paid = discountPrice * clone.getQuantity();
        manager.receivePayment(paid);
        wallet -= paid;
        totalPaid += paid;
        System.out.println("Exchange successfully!");
        checkAccountBalance();
        manager.update();
    }


    private void checkAccountBalance(){
        System.out.printf("\nYou have %.2f dollar(s)\n", wallet);
    }

    private void recharge(){
        if (wallet == MAX_MONEY){
            System.err.println("No need to recharge!"); return;
        }
        System.out.println("How much money you want to recharge: ");
        double charged = InputChecker.inputDoubleInBounds(0.1, MAX_MONEY);
        if (wallet + charged > MAX_MONEY){
            wallet = MAX_MONEY;
        } else {
            wallet += charged;
        }
        checkAccountBalance();

    }


    @Override
    public String toString(){
        return getAccountName() + "\t\t\t" + getLoyalLevel();
    }


    private void showAccountInfo(){
        System.out.println("\nAccount Name: \t "+ getAccountName());
        System.out.printf("Account Balance: %.2f dollar(s)\n", wallet);
        System.out.printf("Total Payment: \t %.2f dollar(s)\n", totalPaid);
        System.out.println("Loyal Customer:  "+getLoyalLevel());
        System.out.println("Discount: \t\t "+getDiscount()+"%");
    }


    @Override
    public void run(){
        if (login() == false){
            return;
        }

        int userChoice;
        do {
            printMenu();
            userChoice = InputChecker.inputIntegerInBounds(0, 10);
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
                case 10:
                    showAccountInfo(); break;
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
        System.out.println("10. Show account info");
        System.out.println("0. Log out");
        System.out.println("Enter customer's choice: ");
    }
}
