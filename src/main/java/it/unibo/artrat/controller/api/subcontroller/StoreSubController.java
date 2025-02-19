package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;

/**
 * controller for the seguent model: store (market).
 * getTypeName, getDescription, getItemName are they are reported in the market controller as well as in the inventory,
 * the difference is that they will be used with getMarket(), as the market is detached from the player and the inventory,
 * as can be seen from the UML scheme.
 * @author Manuel Benagli
 */
public interface StoreSubController extends SubController {

    /**
     * 
     * @return all the game items we can buy in the market view.
     */
    List<Item> purchasableItems();

    /**
     *
     * @param itemToBuy the item we want to buy 
     * @return true if the player can afford the item and it's avaiable, false otherwise.
     */
    boolean buyItem(Item itemToBuy);

    /**
     * 
     * @param passedItem the item passed.
     * @return the name of the item passed.
     */
    String getItemName(Item passedItem);

    /**
     * 
     * @param passedItem the item passed.
     * @return the price of the item passed.
     */
    double getItemPrice(Item passedItem);

    /**
     * 
     * @param passedItem the item passed.
     * @return the item's category (POWERUP or CONSUMABLE).
     */
    String getTypeName(Item passedItem);

    /**
     * 
     * @param passedItem the item which 
     */
    void getDescription(Item passedItem);

    /**
     * 
     * @param choice (creasing or decreasing sorting).
     */
    void sorting(int choice);

    /**
     * 
     * @param type of the item to filter.
     */
    void filterCategory(ItemType type);

    /**
     * 
     * @param nameToSearch a String (using trim and toLowerBound) to search in the list of items.
     */
    void searchItem(String nameToSearch);

}
