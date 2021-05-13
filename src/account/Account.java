package account;

import inputChecker.InputChecker;
import java.util.Scanner;

public abstract class Account {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String WRONG_PASSWORD_MESSAGE = "Wrong passwords!", WRONG_ACCOUNT_NAME_MESSAGE = "Wrong account name!",
            LOG_IN_MESSAGE = "Logged in!", LOG_OUT_MESSAGE = "Logged out!",
            ACCOUNT_NAME_REGEX = "^[a-zA-z](?=\\S+$)[a-zA-Z0-9_].{6,}$", PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",
            ACCOUNT_NAME_RULE = "Account name must start with a letter, contains only numbers, letters or _, and at least 6 characters ",
            PASSWORD_RULE = "Password must contains at least one lowercase letter, one capital letter, one number, one special character, no white space and from 8 to 20 characters";
    public static final int MAX_INPUT_PASSWORD_TIME = 3;


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


    public String getPassword() {
        return password;
    }


    public abstract void run();


    public boolean login() {
        System.out.println("Enter account name: ");
        String inputName = scanner.nextLine();
        if (!inputName.equals(accountName)) {
            System.err.println(WRONG_ACCOUNT_NAME_MESSAGE);
            return false;
        }

        int count = MAX_INPUT_PASSWORD_TIME;
        do {
            System.out.println("Enter password (3 times at most): ");
            String inputPassword = scanner.nextLine();
            if (inputPassword.equals(password)) {
                System.out.println(LOG_IN_MESSAGE);
                return true;
            } else {
                count--;
                System.err.println(WRONG_PASSWORD_MESSAGE);
                System.err.println("You have " + count + " time(s) left");
            }
        } while (count > 0);
        return false;
    }


    public void changeAccountName(){
        System.out.println("Enter current password: ");
        String inputPassword = scanner.nextLine();
        if (!inputPassword.equals(password)){
            System.err.println("Wrong password!");
            return;
        }
        System.out.println("Enter new account name: ");
        accountName = InputChecker.inputString(ACCOUNT_NAME_REGEX, ACCOUNT_NAME_RULE);
        System.out.println("Your account name was changed to "+accountName);
    }


    public void changePassWord(){
        System.out.println("Enter current password: ");
        String inputPassword = scanner.nextLine();
        if (!inputPassword.equals(password)){
            System.err.println("Wrong password!");
            return;
        }
        System.out.println("Enter new password: ");
        password = InputChecker.inputString(PASSWORD_REGEX, PASSWORD_RULE);
        System.out.println("Your password changed!");
    }

}