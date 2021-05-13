package management.observer;

import basicClass.*;
import management.ItemManagement;

import java.util.ArrayList;
import java.util.List;

public abstract class SubItemList {

    protected List<Item> result = new ArrayList<>();

    public abstract void update(ItemManagement itemManagement);

    public List<Item> getSubList(){
        return result;
    }
}
