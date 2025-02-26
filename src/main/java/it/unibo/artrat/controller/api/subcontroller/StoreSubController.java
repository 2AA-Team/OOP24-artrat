package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;

/**
 * ShopSubController for the seguent model: store (market).
 * getTypeName, getDescription, getItemName are they are reported in the market controller as well as in the inventory,
 * the difference is that they will be used with getMarket(), as the shop is detached from the player and the inventory,
 * as can be seen from the UML scheme.
 * 
 * @author Manuel Benagli
 */
public interface StoreSubController extends SubController {

    /**
     * A list of all the purchasable items in the Shop.
     * This list can be sorted, filtered (and filtered with search).
     * If a powerup is bought, the item will be removed from the list.
     * 
     * @return all the game items we can buy in the ShopSubPanel.
     */
    List<Item> purchasableItems();

    /**
     * Method to buy an item in the shop.
     * If a powerup is bought, the item will be removed from the shop.
     * 
     * @param itemToBuy the item we want to buy 
     */
    void buyItem(Item itemToBuy);

    /**
     * Method to get item's name.
     * 
     * @param passedItem the item passed.
     * @return the name of the item passed.
     */
    String getItemName(Item passedItem);

    /**
     * Method to get item's price.
     * 
     * @param passedItem the item passed.
     * @return the price of the item passed.
     */
    double getItemPrice(Item passedItem);

    /**
     * Method to get item's category.
     * 
     * @param passedItem the item passed.
     * @return the item's category (POWERUP or CONSUMABLE).
     */
    ItemType getItemType(Item passedItem);

    /**
     * Method to get item's descripiton.
     * 
     * @param passedItem the item which it will be shown the description.
     */
    void showDescription(Item passedItem);

    /**
     * Method which sorts the item's list, using SortItemStrategy.
     * 
     * @param choice (creasing or decreasing sorting).
     */
    void sorting(int choice);

    /**
     * Method which filter the item's list, using FilterItemStrategy.
     * 
     * @param type of the item to filter.
     */
    void filterCategory(ItemType type);

    /**
     * Method which searchs the item's list, using SearchItemStrategy.
     * 
     * @param nameToSearch a String (using trim and toLowerBound) to search in the list of items.
     */
    void searchItem(String nameToSearch);

    /**
     * Method which initializes the itemList in the ShopSubPanel.
     */
    void initItemList();

    /**
     * Method which gets player's current amount.
     * 
     * @return the player's current amount.
     */
    double getCurrentAmount();
}
