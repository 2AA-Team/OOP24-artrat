package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.characters.Inventory;
import it.unibo.artrat.model.api.market.Item;

/**
 * An implementation of Inventory.
 * @author Cristian Di Donato
 */
public class InventoryImpl implements Inventory {

    private final List<Item> storedItem;

    /**
     * A constructor that initializes an instance of an empty list of items
     */
    public InventoryImpl(){
        this.storedItem = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getStoredItem() {
        return List.copyOf(storedItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addItem(final Item newItem) {
        return storedItem.add(newItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useItem(final Item itemToUse) {
        if (storedItem.contains(itemToUse)) {
            return storedItem.remove(itemToUse);
        }
        return false;
    }
}
