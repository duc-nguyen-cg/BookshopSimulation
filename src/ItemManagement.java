import item.*;
import java.util.*;
import static item.Item.*;

public class ItemManagement {
    public static final String EMPTY_MESSAGE = "The shop is empty!";
    private static final ItemManagement itemManagement = new ItemManagement();
    private static Scanner scanner = new Scanner(System.in);

    private ArrayList<Item> itemList = new ArrayList<>();

    //set up singleton
    private ItemManagement() {}
    public static ItemManagement getInstance(){
        return itemManagement;
    }


    public boolean isEmpty(){
        return itemList.isEmpty();
    }

    //print all items
    public void display(){
        if (isEmpty()){
            System.out.println(EMPTY_MESSAGE);
            return;
        }
        for (int i = 0; i < 10; i++){
            System.out.print("-");
        }
        System.out.println("\nAvailable products: ");
        for (Item item: itemList){
            System.out.println(item);
        }
        for (int i = 0; i < 10; i++){
            System.out.print("-");
        }
        System.out.println();
    }

    //add new item
    public void add(){
        //init new item
        System.out.println("Enter item's code: 1.Book  2.Magazine  3.Newspaper  4.Stationery");
        int choice = InputChecker.inputIntegerInBounds(BOOK_CODE, STATIONERY_CODE);
        Item newItem = ItemGenerator.getItem(choice);
        newItem.inputItemInfo();

        //set ID
        String uniqueID= "";
        while (uniqueID.equals("") | binarySearch(itemList, uniqueID) != null){
            uniqueID = generateRandomID(3);
        }
        newItem.setId(newItem.getId() + uniqueID);

        //add
        itemList.add(newItem);
    }


    //search by ID in any arrayList of items
    private Item binarySearch(ArrayList<Item> list, String id){
        if (!list.isEmpty()) {
            ArrayList<Item> newList = (ArrayList<Item>) list.clone();
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


}
