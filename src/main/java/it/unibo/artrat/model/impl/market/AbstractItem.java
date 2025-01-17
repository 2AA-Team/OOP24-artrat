package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.market.Item;

/**
 * This class is an abstract rappresentation of an market item.
 * Others item extends this class and implement consume.
 * @author Cristian Di Donato
 */
public abstract class AbstractItem implements Item{

    private final String description;
    private final double price;
    private final ItemType itemType;
    
    public AbstractItem(final String description, final double price, final ItemType itemType) {
        this.description = description;
        this.price = price;
        this.itemType = itemType;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public ItemType getType() {
        return this.itemType;
    }

    @Override
    public abstract boolean consume();
    
}
