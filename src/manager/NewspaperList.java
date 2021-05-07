package manager;

import basicClass.*;

public class NewspaperList extends SubItemList {

    public NewspaperList(ItemManagement manager){
        this.itemManager = manager;
        this.itemManager.addObserver(this);
    }

    @Override
    public void update(){
        result.clear();
        for (Item item: itemManager.getItemList()){
            if (item instanceof Newspaper){
                result.add(item);
            }
        }
    }

    @Override
    public void print(){
        System.out.println("\nAvailable newspapers: ");
        super.print();
    }
}
