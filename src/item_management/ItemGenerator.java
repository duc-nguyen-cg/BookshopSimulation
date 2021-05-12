package item_management;

import basicClass.*;
import static basicClass.Item.*;

public class ItemGenerator {
    private ItemGenerator(){};

    public static Item getItem(int itemCode){

        switch (itemCode){
            case BOOK_CODE:
                return new Book();
            case MAGAZINE_CODE:
                return new Magazine();
            case NEWSPAPER_CODE:
                return new Newspaper();
            case STATIONERY_CODE:
                return new Stationery();
            default:
                System.err.println("Such item is not available!");
                return null;
        }
    }

}
