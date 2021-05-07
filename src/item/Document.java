package item;

import java.util.Scanner;

public abstract class Document extends Item{
    private String publisher;

    protected Document() {
        super.setId(DOCUMENT_ID);
    }

    protected Document(String name, int quantity, double price, String publisher) {
        super(name, quantity, price);
        super.setId(DOCUMENT_ID);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", publisher= " + publisher;
    }

    @Override
    public void inputItemInfo(){
        super.inputItemInfo();

        System.out.println("Publisher: ");
        publisher = InputChecker.inputString(MAX_STRING_LENGTH);
    }
}
