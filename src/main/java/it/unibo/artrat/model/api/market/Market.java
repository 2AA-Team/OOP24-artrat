package it.unibo.artrat.model.api.market;

import java.io.IOException;
import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;

/**
 * Market interface.
 * 
 * @author Manuel Benagli
 */
public interface Market {

    /**
     * this method gets a list of all the purchasable items.
     * 
     * @return a list of all the purchasable items.
     */
    List<Item> getPurchItems();

    /**
     * Update my list of items, it's essential for ItemManager.
     * 
     * @param items a list of items.
     */
    void setPurchItems(List<Item> items);

    /**
     * Method which buys an item.
     * If the item's a powerup, the item is removed from the list of purchasable items.
     * 
     * @param passedItem the item I bougth
     * @return true if the purchase operation is done, false otherwise.
     */
    boolean buyItem(Item passedItem);

     /**
     * This method uses ItemReaderImpl to read my yaml file items.yaml.
     * It adds my items (created using the private method createItem) in my list.
     * @throws IOException IOException
     */
    void initMarket();
}
