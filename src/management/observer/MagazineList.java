package management.observer;

import basicClass.*;
import management.ItemManagement;

public class MagazineList extends SubItemList {

    public MagazineList(){ }

    @Override
    public void update(ItemManagement itemManagement){
        result.clear();
        for (Item item: itemManagement.getItemList()){
            if (item instanceof Magazine){
                result.add(item);
            }
        }
    }
}
