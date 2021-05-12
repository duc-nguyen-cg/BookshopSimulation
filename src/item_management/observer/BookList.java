package item_management.observer;

import basicClass.*;
import item_management.ItemManagement;

public class BookList extends SubItemList {

    public BookList(){}

    @Override
    public void update(ItemManagement itemManagement){
        result.clear();
        for (Item item: itemManagement.getItemList()){
            if (item instanceof Book){
                result.add(item);
            }
        }
    }
}
