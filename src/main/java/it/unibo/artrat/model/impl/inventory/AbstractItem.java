package it.unibo.artrat.model.impl.inventory;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * An abstract class that will be the base class for implementing other objects and consumables.
 */
public abstract class AbstractItem implements Item {

    private final String description;
    private final double price;
    private final ItemType itemType;

    /**
     * A constructor that initialize a new istance of Item with passed variable.
     * @param desc the description of new item.
     * @param price the price of new item.
     * @param itemType the itemtype of new item.
     */
    protected AbstractItem(final String desc, final double price, final ItemType itemType) {
        this.description = desc;
        this.price = price;
        this.itemType = itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice() {
        return this.price;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getType() {
        return this.itemType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Player consume(Player player);
}
