package item;

import java.util.Scanner;

public class Book extends Document {
    private String author;
    private String category;
    private int pageNum;

    public Book() {
        super.setId(super.getId()+BOOK_CODE);
    }

    public Book(String name, int quantity, double price, String publisher, String author, String category, int pageNum) {
        super(name, quantity, price, publisher);
        super.setId(super.getId()+BOOK_CODE);
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Author: ");
        author = scanner.nextLine();
        System.out.println("Category: ");
        category = scanner.nextLine();
        System.out.println("Number of pages: ");
        pageNum = InputChecker.inputIntegerInBounds(1, Integer.MAX_VALUE);
    }
}
