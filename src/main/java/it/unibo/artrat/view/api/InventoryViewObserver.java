package it.unibo.artrat.view.api;

import java.util.List;

import it.unibo.artrat.model.api.market.Item;

/**
 * An interface for InvetoryObserver for InvetorySubPanel.
 * @author Cristian Di Donato.
 */

public interface InventoryViewObserver {

    /**
     * A method to retrieve the list of items in the inventory from the model.
     * @return Inventory item list.
     */
    List<Item> getStoredItem();

    /**
     * 
     * @param passedItem The item that needs to be used and removed from the inventory.
     * @return True if the item is used; false otherwise.
     */
    boolean useItem(Item passedItem);

    /**
     * 
     * @param passedItem The item for which we need to get the ItemType.
     * @return the name of the ItemType of the Item.
     */
    String getTypeName(Item passedItem);

    /**
     * Method to retrieve the description of the passed item.
     * @param passedItem
     */
    void readDescription(Item passedItem);

    /**
     * Method to notify the model of window closure and trigger the view closing method.
     */
    void quit();
}
