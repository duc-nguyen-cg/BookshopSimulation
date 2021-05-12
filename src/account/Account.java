package account;

import java.util.Scanner;

public class Account {
    private static final Scanner scanner = new Scanner(System.in);

    private String accountName;
    private String password;

    public Account() {
    }


    public Account(String accountName, String password) {
        this.accountName = accountName;
        this.password = password;
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

    public void run(){};


    public boolean login(){
        System.out.println("Enter account name: ");
        String inputName = scanner.nextLine();
        if (!inputName.equals(accountName)){
            System.err.println("Wrong account name!");
            return false;
        }

        System.out.println("Enter password: ");
        String inputPassword = scanner.nextLine();
        if (inputPassword.equals(password)){
            System.out.println("Logged in!");
            return true;
        }
        System.err.println("Wrong passwords!");
        return false;
    }
}