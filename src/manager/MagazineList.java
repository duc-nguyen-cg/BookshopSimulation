package manager;

import basicClass.*;

public class MagazineList extends SubItemList {

    public MagazineList(ItemManagement manager){
        this.itemManager = manager;
        this.itemManager.addObserver(this);
    }

    @Override
    public void update(){
        result.clear();
        for (Item item: itemManager.getItemList()){
            if (item instanceof Magazine){
                result.add(item);
            }
        }
    }

    @Override
    public void print(){
        System.out.println("\nAvailable magazines: ");
        super.print();
    }
}
