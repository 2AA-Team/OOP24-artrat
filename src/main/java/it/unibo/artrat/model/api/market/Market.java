package it.unibo.artrat.model.api.market;

import java.io.IOException;
import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;

/**
 * Market interface.
 */
public interface Market {

    /**
     * 
     * @return a list of all the purchasable items.
     */
    List<Item> getPurchItems();

    /**
     * Update my list of items, it's essential for ItemManager.
     * @param items items
     */
    void setPurchItems(List<Item> items);

    /**
     * 
     * @param passedItem the item I bougth
     * @return true if the purchase operation is done
     */
    boolean buyItem(Item passedItem);

     /**
     * This method uses ItemReaderImpl to read my yaml file items.yaml.
     * It adds my items (created using the private method createItem) in my list.
     * @throws IOException
     */
    void initMarket();
}
