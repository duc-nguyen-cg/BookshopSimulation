package management.observer;

import basicClass.*;
import management.ItemManagement;

public class NewspaperList extends SubItemList {

    public NewspaperList(){}

    @Override
    public void update(ItemManagement itemManagement){
        result.clear();
        for (Item item: itemManagement.getItemList()){
            if (item instanceof Newspaper){
                result.add(item);
            }
        }
    }
}
