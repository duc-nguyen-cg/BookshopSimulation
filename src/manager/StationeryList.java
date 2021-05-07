package manager;

import basicClass.Item;
import basicClass.Stationery;

public class StationeryList extends SubItemList {

    public StationeryList(ItemManagement manager){
        this.itemManager = manager;
        this.itemManager.addObserver(this);
    }

    @Override
    public void update(){
        result.clear();
        for (Item item: itemManager.getItemList()){
            if (item instanceof Stationery){
                result.add(item);
            }
        }
    }

    @Override
    public void print(){
        System.out.println("\nAvailable stationery: ");
        super.print();
    }
}
