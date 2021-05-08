package basicClass;

import inputChecker.InputChecker;

import java.io.Serializable;

public abstract class Item implements Serializable {
    public static final int BOOK_CODE = 1, MAGAZINE_CODE = 2, NEWSPAPER_CODE = 3, STATIONERY_CODE = 4
            , MAX_STRING_LENGTH = 30, UNIQUE_ID_LENGTH = 3
            , MIN_QUANTITY = 1, MAX_QUANTITY = Integer.MAX_VALUE
            , MIN_PAGE_NUM = 1, MAX_PAGE_NUM = Integer.MAX_VALUE
            , MIN_NO = 1, MAX_NO = Integer.MAX_VALUE;
    public static final double MIN_PRICE = 0.1, MAX_PRICE = Double.MAX_VALUE;
    public static final String BOOK_ID = "D1", MAGAZINE_ID = "D2", NEWSPAPER_ID = "D3", STATIONERY_ID = "S"
            , BOOK_ID_REGEX = "^D1(.*?)$", MAGAZINE_ID_REGEX = "^D2(.*?)$", NEWSPAPER_ID_REGEX = "^D3(.*?)$", STATIONERY_ID_REGEX = "^S(.*?)$" ;

    private String id = "", name;
    private int quantity;
    private double price;

    protected Item() {
    }

    protected Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return  id + ".\t" +
                "name= " + name+
                ", quantity= " + quantity+
                ", price= " + price;
    }

    public void inputItemInfo(){
        System.out.println("Name: ");
        name = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Quantity: ");
        quantity = InputChecker.inputIntegerInBounds(MIN_QUANTITY, MAX_QUANTITY);
        System.out.println("Price: ");
        price = InputChecker.inputDoubleInBounds(MIN_PRICE, MAX_PRICE);
    }
}
