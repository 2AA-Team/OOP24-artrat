package it.unibo.artrat.model.impl.inventory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * An implementation of Inventory.
 * @author Cristian Di Donato
 */
public class InventoryImpl implements Inventory {

    private final List<Item> storedItem;
    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryImpl.class);

    /**
     * A constructor that initializes an instance of an empty list of items.
     */
    public InventoryImpl() {
        this.storedItem = new ArrayList<>();
    }

    /**
     * A constructor that initializes an instance.
     * @param inv the invetory where get the list of item.
     */
    public InventoryImpl(final Inventory inv) {
        this.storedItem = inv.getStoredItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getStoredItem() {
        LOGGER.info("Request for the entire list of saved items.");
        return new ArrayList<>(storedItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addItem(final Item newItem) {
        LOGGER.info("Request to add item : " + newItem.getClass().getSimpleName());
        return storedItem.add(newItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useItem(final Item itemToUse) {
        LOGGER.info("Request to remove item  : " + itemToUse.getClass().getSimpleName());
        if (storedItem.contains(itemToUse)) {
            storedItem.remove(itemToUse);
            LOGGER.info("Item : " + itemToUse.getClass().getSimpleName() + " removed");
            return true;
        }
        return false;
    }
}
