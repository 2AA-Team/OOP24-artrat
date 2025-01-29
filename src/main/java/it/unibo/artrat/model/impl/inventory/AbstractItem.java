package it.unibo.artrat.model.impl.inventory;

import it.unibo.artrat.model.api.inventory.ItemType;

import it.unibo.artrat.model.api.inventory.Item;

/**
 * An abstract class that will be the base class for implementing other objects and consumables.
 */
public abstract class AbstractItem implements Item {

    private final String description;
    private final double price;
    private final ItemType itemType;

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
    public abstract boolean consume();
}
