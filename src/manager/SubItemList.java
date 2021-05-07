package manager;

import basicClass.*;

import java.util.ArrayList;
import java.util.List;

public abstract class SubItemList {
    private final String EMPTY_MESSAGE = "The list is empty!";

    public List<Item> result = new ArrayList<>();
    protected ItemManagement itemManager;

    protected abstract void update();

    public void print(){
        if (result.isEmpty()){
            System.err.println(EMPTY_MESSAGE);
            return;
        }
        for (Item item: result){
            System.out.println(item);
        }
    }
}
