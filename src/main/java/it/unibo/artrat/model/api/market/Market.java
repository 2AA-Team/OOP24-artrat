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
     * 
     * @param passedItem the item I bougth
     * @return true if the purchase operation is done
     */
    boolean buyItem(Item passedItem);
}
