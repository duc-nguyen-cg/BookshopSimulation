package item;

public class Magazine extends Document{
    private int no;
    private String releaseMonth;

    public Magazine() {
        super.setId(super.getId()+MAGAZINE_CODE);
    }

    public Magazine(String name, int quantity, double price, String publisher, int no, String releaseMonth) {
        super(name, quantity, price, publisher);
        super.setId(super.getId()+MAGAZINE_CODE);
        this.no = no;
        this.releaseMonth = releaseMonth;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(String releaseMonth) {
        this.releaseMonth = releaseMonth;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", no." + no +
                ", " + releaseMonth;
    }

    @Override
    public void inputItemInfo() {
        super.inputItemInfo();

        System.out.println("No: ");
        no = InputChecker.inputIntegerInBounds(1, Integer.MAX_VALUE);
        System.out.println("Released month: ");
        releaseMonth = InputChecker.inputMonth();
    }
}
