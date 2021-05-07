package basicClass;

import inputChecker.InputChecker;

public class Book extends Document {
    private String author;
    private String category;
    private int pageNum;

    public Book() {
        super.setId(BOOK_ID);
    }

    public Book(String name, int quantity, double price, String publisher, String author, int pageNum, String category) {
        super(name, quantity, price, publisher);
        super.setId(BOOK_ID);
        this.author = author;
        this.category = category;
        this.pageNum = pageNum;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", author= " + author +
                ", category= " + category +
                ", number of pages= " + pageNum;
    }

    @Override
    public void inputItemInfo() {
        super.inputItemInfo();

        System.out.println("Author: ");
        author = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Category: ");
        category = InputChecker.inputString(MAX_STRING_LENGTH);
        System.out.println("Number of pages: ");
        pageNum = InputChecker.inputIntegerInBounds(MIN_PAGE_NUM, MAX_PAGE_NUM);
    }
}
