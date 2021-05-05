package item;

import java.util.Scanner;

public abstract class Document extends Item{
    private String publisher;

    public Document() {
        super.setId(DOCUMENT_ID);
    }

    public Document(String name, int quantity, double price, String publisher) {
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Publisher: ");
        publisher = scanner.nextLine();
    }
}
