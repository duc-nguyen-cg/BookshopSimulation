package basicClass;

import inputChecker.InputChecker;

public class Newspaper extends Document{
    private String releaseDate;

    public Newspaper() {
        super.setId(NEWSPAPER_ID);
    }

    public Newspaper(String name, int quantity, double price, String publisher, String releaseDate) {
        super(name, quantity, price, publisher);
        super.setId(NEWSPAPER_ID);
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", " + releaseDate;
    }

    @Override
    public void inputItemInfo(){
        super.inputItemInfo();

        System.out.println("Released date: ");
        releaseDate = InputChecker.inputDate();
    }
}
