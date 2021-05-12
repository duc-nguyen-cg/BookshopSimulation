package management;

import IOTask.*;
import inputChecker.InputChecker;
import basicClass.*;
import management.observer.*;
import java.util.*;

import static IOTask.BinaryFileTask.BINARY_FILEPATH_REGEX;
import static IOTask.CSVFileTask.CSV_FILEPATH_REGEX;
import static basicClass.Item.*;

public class ItemManagement {
    public static final String EMPTY_MESSAGE = "Empty!";
    public static final String NOT_FOUND_MESSAGE = "Not found!";
    private static Scanner scanner = new Scanner(System.in);

    private List<Item> itemList = new ArrayList<>();
    private double profit = 0;

    //set up singleton
    private static final ItemManagement itemManager = new ItemManagement();

    private ItemManagement() {}

    public static ItemManagement getInstance(){
        itemManager.attach(new BookList());
        itemManager.attach(new MagazineList());
        itemManager.attach(new NewspaperList());
        itemManager.attach(new StationeryList());
        return itemManager;
    }


    //set up observers
    private List<SubItemList> observers = new ArrayList<>();

    private void attach(SubItemList o){
        if (!observers.contains(o)){
            observers.add(o);
        }
    }

    private void detach(SubItemList o){
        if (observers.contains(o)){
            observers.remove(o);
        }
    }

    private void update(){
        for (SubItemList observer: observers){
            observer.update(itemManager);
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
        List<Item> chosenList = chooseDataList();
        print(chosenList);
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

        //add, sort by ID and inform
        itemList.add(newItem);
        itemList.sort(Comparator.comparing(Item::getId));
        System.out.println("Added '"+ newItem +"' successfully!");
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
            System.err.println(NOT_FOUND_MESSAGE);
        } else {
            itemList.remove(removeItem);
            System.out.println("Removed '"+ removeItem+ "' successfully!");
        }
        update();
    }


    //edit information
    public void edit(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        System.out.println("Enter ID to edit: ");
        String editID = scanner.nextLine();
        Item editItem = binarySearch(itemList, editID);
        if (editItem == null){
            return;
        }
        editItem.inputItemInfo();
        update();
    }


    //search
    public void search(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }

        List<Item> result;
        System.out.println("Search by:   1.ID  2.Name");
        int choice = InputChecker.inputIntegerInBounds(1,2);
        if (choice == 1){
            result = searchByID();
        } else {
            result = searchByName();
        }
        print(result);
    }

    //search an item by ID
    public List<Item> searchByID(){
        List<Item> found = new ArrayList<>();

        System.out.println("Enter ID to search: ");
        String searchID = scanner.nextLine();
        List<Item> targetList;
        if (searchID.matches(BOOK_ID_REGEX)){
            targetList = observers.get(0).getSubList();
        } else if (searchID.matches(MAGAZINE_ID_REGEX)){
            targetList = observers.get(1).getSubList();
        } else if (searchID.matches(NEWSPAPER_ID_REGEX)){
            targetList = observers.get(2).getSubList();
        } else if (searchID.matches(STATIONERY_ID_REGEX)){
            targetList = observers.get(3).getSubList();
        } else {
            System.err.println(NOT_FOUND_MESSAGE);
            return null;
        }

        Item foundItem = binarySearch(targetList, searchID);
        found.add(foundItem);
        return found;
    }


    //search by name
    private List<Item> searchByName(){
        List<Item> found = new ArrayList<>();
        System.out.println("Enter keywords to search: ");
        String searchKey = InputChecker.inputString(MAX_STRING_LENGTH);

        for (Item item: itemList){
            if (matchWords(item.getName(), searchKey)){
                found.add(item);
            }
        }
        return found;
    }


    //sort item list
    public void sort(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        List<Item> chosenList = new ArrayList<>(chooseDataList());
        System.out.println("Sort by: 0.ID (default)   1.Name   2.Quantity   3.Price ");
        int choice = InputChecker.inputIntegerInBounds(0,3);
        switch (choice){
            case 0:
                chosenList.sort(Comparator.comparing(Item::getId)); break;
            case 1:
                chosenList.sort(Comparator.comparing(Item::getName)); break;
            case 2:
                chosenList.sort(Comparator.comparing(Item::getQuantity)); break;
            case 3:
                chosenList.sort(Comparator.comparing(Item::getPrice)); break;
        }
        print(chosenList);
    }


    //write into file
    public void exportData(){
        if (isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        //choose list to export
        List<Item> exportedList = chooseDataList();

        //choose filetype, set up writer
        IOTaskWithItem writer;
        String dest;
        System.out.println("Choose type of file:  1.Binary  2.CSV");
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
        writer.write(exportedList, dest);
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


    //clear all data
    public void clearAll(){
        System.err.println("Are you sure?     1.Continue   0.Cancel");
        int choice = InputChecker.inputIntegerInBounds(0, 1);
        if (choice == 1) {
            itemList.clear();
            System.err.println(EMPTY_MESSAGE);
        }
        update();
    }


    public double getProfit(){
        return profit;
    }


    public void receivePayment(double payment){
        profit = profit + payment;
    }
    
    private boolean matchWords(String target, String key){
        String[] keyWords = key.split(" ");
        String[] targetWords = target.split(" ");

        for (String targetWord: targetWords){
            for (String keyWord: keyWords){
                if ((targetWord.trim().toLowerCase()).equals(keyWord.trim().toLowerCase())){
                    return true;
                }
            }
        }
        return false;
    }
    

    private List<Item> chooseDataList(){
        System.out.println("Enter list to display: 0.Book  1.Magazine  2.Newspaper  3.Stationery  4.All");
        int choice = InputChecker.inputIntegerInBounds(0,4);
        switch (choice){
            case 0:
                return observers.get(0).getSubList();
            case 1:
                return observers.get(1).getSubList();
            case 2:
                return observers.get(2).getSubList();
            case 3:
                return observers.get(3).getSubList();
            case 4:
                return itemList;
        }
        return null;
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

}

