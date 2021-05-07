package item;

import java.util.Scanner;

public class Stationery extends Item{
    private String producer;
    private String type;

    public Stationery() {
        super.setId(STATIONERY_ID);
    }

    public Stationery(String name, int quantity, double price, String producer, String type) {
        super(name, quantity, price);
        super.setId(STATIONERY_ID);
        this.producer = producer;
        this.type = type;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", producer= " + producer +
                ", type= " + type;
    }

    @Override
    public void inputItemInfo(){
        super.inputItemInfo();

        System.out.println("Producer: ");
        producer = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Type: ");
        type = InputChecker.inputString(MAX_STRING_LENGTH);
    }
}
