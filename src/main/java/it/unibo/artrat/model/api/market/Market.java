package it.unibo.artrat.model.api.market;

import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;

/**
 * Market interface
 */
public interface Market {
    
    /**
     * 
     * @return a list of all the purchasable items
     */
    List<Item> getPurchItems();

    /**
     * If I buy a powerup, it will be removed in the shop
     * @param passedItem
     * @return
     */
    Item removeItem(Item passedItem);

    /**
     * 
     * @param passedItem
     * @return
     */
    boolean isOutOfStock(Item passedItem);
    //devo fare poi il refill dei consumabili
}
