package it.unibo.artrat.model.api.market;

import it.unibo.artrat.model.impl.ItemType;

/**
 * Class that rappresents shop/inventory item.
 * @author Cristian Di Donato
 */
public interface Item {

    /**
     * 
     * @return String that rappresent the description of object: Effect, Story, Curiosity
     */
    public String getDescription();

    /**
     * 
     * @return the price of the item.
     */
    public double getPrice();

    /**
     * 
     * @return the ItemType of the current Item: can be Consumable or PowerUp
     */
    public ItemType getType();

    /**
     * 
     * @return true: if the item is consume; false: if the item can't be consume for some reason;
     */
    public boolean consume();


}
