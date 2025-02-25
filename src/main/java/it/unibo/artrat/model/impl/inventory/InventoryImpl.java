package it.unibo.artrat.model.impl.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * An implementation of Inventory interface.
 * 
 * @author Cristian Di Donato
 */
public class InventoryImpl implements Inventory {

    private final List<Item> storedItem;

    /**
     * A constructor that initializes an instance of an empty list of items.
     */
    public InventoryImpl() {
        this.storedItem = new ArrayList<>();
    }

    /**
     * A constructor that initializes an instance.
     * 
     * @param inv the invetory where get the list of item.
     */
    public InventoryImpl(final Inventory inv) {
        this.storedItem = inv.getStoredItem();
    }

    private List<Item> sortItems() {
        return new ArrayList<>(storedItem.stream()
                        .sorted((x, y) -> x.getClass().getSimpleName().compareTo(y.getClass().getSimpleName()))
                        .collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getStoredItem() {
        return new ArrayList<>(sortItems());
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
            storedItem.remove(itemToUse);
            return true;
        }
        return false;
    }
}
