package management;

import account.*;
import basicClass.Item;

import java.util.*;

import static account.Account.LOG_IN_MESSAGE;
import static account.Account.WRONG_PASSWORD_MESSAGE;


public class AccountManagement {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String EMPTY_MESSAGE = "Empty!";

    private List<CustomerAccount> customers = new ArrayList<>();

    //set up singleton
    private static final AccountManagement accountManager = new AccountManagement();

    private AccountManagement(){}

    public static AccountManagement getInstance(){
        return accountManager;
    }


    public void display(){
        if (customers.isEmpty()){
            System.out.println(EMPTY_MESSAGE);
        }
        for (CustomerAccount account: customers){
            System.out.println(account);
        }
    }


    public void add(CustomerAccount account){
        customers.add(account);
    }

    public void add(List<CustomerAccount> accounts){
        customers.addAll(accounts);
    }

    public void add(){
//        CustomerAccount newAccount = new CustomerAccount();
//        newAccount.inputAccountInfo();
//        customers.add(newAccount);
    }


    public void remove(CustomerAccount account){
        customers.remove(account);
    }


    public void loginControl(){
        System.out.println("Enter account name: ");
        String inputAccountName = scanner.nextLine();
        CustomerAccount found = binarySearch(customers, inputAccountName);
        if (found == null){
            System.err.println("No such account exist!");
            return;
        }


        int count = 3;
        System.out.println("Enter password: ");
        do {
            System.out.println("Enter password (3 times at most): ");
            String inputPassword = scanner.nextLine();
            if (inputPassword.equals(found.getPassword())) {
                System.out.println(LOG_IN_MESSAGE);
                found.run();
            } else {
                count--;
                System.err.println(WRONG_PASSWORD_MESSAGE);
                System.err.println("You have " + count + " time(s) left");
            }
        } while (count > 0);

    }


    private CustomerAccount binarySearch(List<CustomerAccount> list, String accountName){
        if (!list.isEmpty()) {
            List<CustomerAccount> newList = new ArrayList<>(list);
            Collections.sort(newList, (o1, o2) -> o1.getAccountName().compareTo(o2.getAccountName()));  //sort by ID

            int left = 0;
            int right = newList.size() - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                int res = accountName.compareTo(newList.get(middle).getAccountName());
                if (res < 0) {
                    right = middle - 1;
                } else if (res > 0) {
                    left = middle + 1;
                } else {
                    return newList.get(middle);
                }
            }
        }
        return null;
    }



}
