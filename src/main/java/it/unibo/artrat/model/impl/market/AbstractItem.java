package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Item;

public abstract class AbstractItem implements Item{

    private final String description="";
    private final double price=0.0;
    private final ItemType itemType=null;


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
