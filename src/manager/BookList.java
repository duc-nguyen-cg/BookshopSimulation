package manager;

import basicClass.*;

public class BookList extends SubItemList {

    public BookList(ItemManagement manager){
        this.itemManager = manager;
        this.itemManager.addObserver(this);
    }

    @Override
    public void update(){
        result.clear();
        for (Item item: itemManager.getItemList()){
            if (item instanceof Book){
                result.add(item);
            }
        }
    }

    @Override
    public void print(){
        System.out.println("\nAvailable books: ");
        super.print();
    }

}
