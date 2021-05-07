package itemManagement;

import IOTask.*;
import inputCheck.InputChecker;
import item.*;

import java.util.*;
import static IOTask.BinaryFileTask.BINARY_FILEPATH_REGEX;
import static IOTask.CSVFileTask.CSV_FILEPATH_REGEX;
import static item.Item.*;

public class ItemManagement {
    public static final String EMPTY_MESSAGE = "The list is empty!";
    private static Scanner scanner = new Scanner(System.in);

    private List<Item> itemList = new ArrayList<>();


    //set up singleton
    private static final ItemManagement itemManager = new ItemManagement();
    private ItemManagement() {}
    public static ItemManagement getInstance(){
        return itemManager;
    }


    //set up observers
    private List<SubItemList> observers = new ArrayList<>();
    public void addObserver(SubItemList o){
        observers.add(o);
    }
    private void update(){
        for (SubItemList observer: observers){
            observer.update();
        }
    }


    //return a clone of the item list
    public List<Item> getItemList(){
        List<Item> clone = new ArrayList<>(itemList);
        return clone;
    }


    //check if the list is empty
    public boolean isEmpty(){
        return itemList.isEmpty();
    }


    //print items by type
    public void display(){
        System.out.println("Enter list to display: 0.Book  1.Magazine  2.Newspaper  3.Stationery  4.All");
        int choice = InputChecker.inputIntegerInBounds(0,4);

        switch (choice){
            case 0:
                observers.get(0).print(); break;
            case 1:
                observers.get(1).print(); break;
            case 2:
                observers.get(2).print(); break;
            case 3:
                observers.get(3).print(); break;
            case 4:
                print(itemList); break;
        }
    }


    //add new item
    public void add(){
        //init new item
        System.out.println("Enter item's code: 1.Book  2.Magazine  3.Newspaper  4.Stationery");
        int choice = InputChecker.inputIntegerInBounds(BOOK_CODE, STATIONERY_CODE);
        Item newItem = ItemGenerator.getItem(choice);
        newItem.inputItemInfo();

        //set ID
        String commonID = newItem.getId();
        String uniqueID = "";
        while (uniqueID.equals("") | binarySearch(itemList, commonID + uniqueID) != null){
            uniqueID = generateRandomID(UNIQUE_ID_LENGTH);
        }
        newItem.setId(commonID + uniqueID);

        //add and re-sort the list by ID (default)
        System.out.println("Added '"+ newItem +"' successfully!");
        itemList.add(newItem);
        itemList.sort(Comparator.comparing(Item::getId));
        update();
    }


    //remove an item
    public void removeByID(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Enter ID to remove: ");
        String removeID = scanner.nextLine();
        Item removeItem = binarySearch(itemList, removeID);
        if (removeItem == null){
            System.err.println("Not found!");
        } else {
            itemList.remove(removeItem);
            System.out.println("Removed '"+ removeItem+ "' successfully!");
        }
        update();
    }


    //search an item by ID
    public void searchByID(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Enter ID to search: ");
        String searchID = scanner.nextLine();
        Item searchItem = binarySearch(itemList, searchID);
        if (searchItem == null){
            System.err.println("Not found!");
        } else {
            System.out.println(searchItem);
        }
    }


    //search by name
    public void searchByName(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Enter the name of item: ");
        String searchKey = scanner.nextLine();
        searchKey = searchKey.trim();
        String[] searchWords = searchKey.split(" ");
        for (Item item: itemList){

        }
    }


    //sort item list
    public void sort(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Sort by: 0.ID (default)   1.Name   2.Quantity   3.Price ");
        int choice = InputChecker.inputIntegerInBounds(0,3);
        List<Item> newList = new ArrayList<>(itemList);
        switch (choice){
            case 0:
                newList.sort(Comparator.comparing(Item::getId));
                itemList = new ArrayList<>(newList);
                break;
            case 1:
                newList.sort(Comparator.comparing(Item::getName)); break;
            case 2:
                newList.sort(Comparator.comparing(Item::getQuantity)); break;
            case 3:
                newList.sort(Comparator.comparing(Item::getPrice)); break;
        }
        print(newList);
    }


    //write into file
    public void exportData(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        IOTaskWithItem writer;
        String dest;

        System.out.println("Choose type of dest:  1.Binary  2.CSV");
        int choice = InputChecker.inputIntegerInBounds(1,2);
        if (choice == 1){
            System.out.println("Enter output filepath ends with .txt: ");
            dest = InputChecker.inputString(BINARY_FILEPATH_REGEX);
            writer = BinaryFileTask.getInstance();
        } else {
            System.out.println("Enter output filepath ends with .csv: ");
            dest = InputChecker.inputString(CSV_FILEPATH_REGEX);
            writer = CSVFileTask.getInstance();
        }
        writer.write(itemList, dest);
    }


    //read from file
    public void importData(){
        IOTaskWithItem reader;
        String source;

        System.out.println("Choose type of file:  1.Binary  2.CSV");
        int choice = InputChecker.inputIntegerInBounds(1,2);
        if (choice == 1){
            System.out.println("Enter input filepath ends with .txt: ");
            source = InputChecker.inputString(BINARY_FILEPATH_REGEX);
            reader = BinaryFileTask.getInstance();
        } else {
            System.out.println("Enter input filepath ends with .csv: ");
            source = InputChecker.inputString(CSV_FILEPATH_REGEX);
            reader = CSVFileTask.getInstance();
        }
        itemList = reader.read(itemList, source);
        update();
    }


    //edit information
    public void edit(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Enter ID: ");
        String editID = scanner.nextLine();
        Item editItem = binarySearch(itemList, editID);
        if (editItem == null){
            return;
        }
        editItem.inputItemInfo();
        update();
    }


    //search by ID in any List of items
    private Item binarySearch(List<Item> list, String id){
        if (!list.isEmpty()) {
            List<Item> newList = new ArrayList<>(itemList);
            Collections.sort(newList, (o1, o2) -> o1.getId().compareTo(o2.getId()));  //sort by ID

            int left = 0;
            int right = newList.size() - 1;
            while (left <= right) {
                int middle = (left + right) / 2;
                int res = id.compareTo(newList.get(middle).getId());
                if (res < 0) {
                    right = middle - 1;
                } else if (res > 0) {
                    left = middle + 1;
                } else {
                    return newList.get(middle);
                }
            }
        }
        return null;
    }


    //generate random ID of any length
    private String generateRandomID(int length){
        int leftLimit = 48; //ASCII code for 0
        int rightLimit = 57;  //ASCII code for 9
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i < length; i++){
            int randomInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomInt);
        }
        return buffer.toString();
    }


    //print any list
    private void print(List<Item> list){
        System.out.println("\nAvailable items: ");
        if (list.isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        Iterator<Item> iterator = list.iterator();
        while (iterator.hasNext()){
            Item item = iterator.next();
            System.out.println(item);
        }
        System.out.println();
    }


    public void testList(){
        itemList.add(new Book("1984", 10, 5.0, "London", "Orwell", 100, "Dystopia"));
        itemList.add(new Magazine("Newsweek", 100, 2.5, "New York", 2, "3/2018" ));
        itemList.add(new Newspaper("Wall Street Journal", 50, 2.99, "New York", "7/7/1992"));
        itemList.add(new Stationery("Pencil", 200, 1.5, "Hong Ha", "2B"));
    }


}
