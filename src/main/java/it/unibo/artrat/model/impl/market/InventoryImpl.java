package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.characters.Inventory;
import it.unibo.artrat.model.api.market.Item;

/**
 * An implementation of Inventory
 * @author Cristian Di Donato
 */
public class InventoryImpl implements Inventory{

    private final List<Item> storedItem;

    public InventoryImpl(){
        storedItem = new ArrayList<>();
    }

    @Override
    public List<Item> getStoredItem() {
        return this.storedItem;
    }

    @Override
    public boolean addItem(Item newItem) {
        return storedItem.add(newItem);
    }

    @Override
    public boolean useItem(Item itemToUse) {
        if(storedItem.contains(itemToUse)){
            return storedItem.remove(itemToUse);
        }
        return false;
    }
    
}
