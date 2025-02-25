package it.unibo.artrat.model.api.market;

import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;

/**
 * ItemManager interface, manages the list of game items.
 * Game items are read by the item reader.
 *
 * SortItemPrice, searchItems and filterItems are dependent one another.
 */
public interface ItemManager {

    /**
     * 
     * @param dir sets creasing and reversing sorting.
     * @return a list of items sorted by price.
     */
    List<Item> sortItemPrice(int dir);

    /**
     * This private method is used to coordinate filter and search. 
     * When I have to filter an item, I apply this method first with search private method of
     * the passedList as parameter.
     * 
     * @param itemType the type of item (POWERUP or CONSUMABLE).
     * @return A List of POWERUP or CONSUMABLE items.
     */
    List<Item> filterItems(ItemType itemType);

    /**
     * This private method is used to coordinate filter and search.
     * When I have to search an item, I apply this method first with filter private method of
     * the passedList as parameter.
     * The search is based character by character starting from the beginning.
     * There are no spaces and uppercase and lowercase letters don't matter.
     * 
     * @param nameToSearch the name of the item to search.
     * @return A List of items with characters corresponding to those typed.
     */
    List<Item> searchItem(String nameToSearch); 

    /**
     * @param passedList the list which will be updated.
     */
    void updateItemList(List<Item> passedList);
}
