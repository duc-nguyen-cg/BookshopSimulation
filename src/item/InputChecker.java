package item;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker {
    private static final String ERROR_MESSAGE = "Invalid input, please enter again: ";

    private static final String DATE_REGEX = "^(0?[1-9]|1[012])[/-](0?[1-9]|[12][0-9]|3[01])[/-]([12]\\d\\d\\d)$";
    private static final String DATE_FORMAT = "mm/dd/yyyy or mm-dd-yyyy";

    private static final String MONTH_REGEX = "^(0?[1-9]|1[012])[/-]([12]\\d\\d\\d)$";
    private static final String MONTH_FORMAT = "mm/yyyy or mm-yyyy";

    private static Scanner scanner = new Scanner(System.in);

    public static int inputIntegerInBounds(int min, int max){
        int result = -1;
        do {
            try {
                result = scanner.nextInt();
                if (result < min | result > max){
                    System.err.println(ERROR_MESSAGE);
                }
            } catch (InputMismatchException e){
                System.err.println(ERROR_MESSAGE);
            }
            scanner.nextLine();
        } while (result < min | result > max);
        return result;
    }


    public static double inputDoubleInBounds(double min, double max){
        double result = 0;
        do {
            try {
                result = scanner.nextDouble();
                if (result < min | result > max){
                    System.err.println(ERROR_MESSAGE);
                }
            } catch (InputMismatchException e){
                System.err.println(ERROR_MESSAGE);
            }
            scanner.nextLine();
        } while (result < min | result > max);
        return result;
    }



    public static String inputDate(){
        String str;
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher;
        boolean isDate;
        do {
            str = scanner.nextLine();
            matcher = pattern.matcher(str);
            isDate = matcher.matches();
            if (!isDate){
                System.err.println(ERROR_MESSAGE + DATE_FORMAT);
            }
        } while (!isDate);
        return str;
    }


    public static String inputMonth(){
        String str;
        Pattern pattern = Pattern.compile(MONTH_REGEX);
        Matcher matcher;
        boolean isMonth;
        do {
            str = scanner.nextLine();
            matcher = pattern.matcher(str);
            isMonth = matcher.matches();
            if (!isMonth){
                System.err.println(ERROR_MESSAGE + MONTH_FORMAT);
            }
        } while (!isMonth);
        return str;
    }
}
