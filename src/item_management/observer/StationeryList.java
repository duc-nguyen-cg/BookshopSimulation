package item_management.observer;

import basicClass.Item;
import basicClass.Stationery;
import item_management.ItemManagement;

public class StationeryList extends SubItemList {

    public StationeryList(){}

    @Override
    public void update(ItemManagement itemManagement){
        result.clear();
        for (Item item: itemManagement.getItemList()){
            if (item instanceof Stationery){
                result.add(item);
            }
        }
    }
}
