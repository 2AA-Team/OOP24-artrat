package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * controller for the seguent model: store (market).
 */
public interface StoreSubController extends SubController{

    /**
     * 
     * @return all the game items we can buy in the market view
     */
    List<Item> purchasableItems();

    /**
     *
     * @param itemToBuy the item we want to buy 
     * @return true if the player can afford the item and it's avaiable, false otherwise 
     */
    boolean buyItem(Item itemToBuy);

    /*      da fare poi eventualmente
    boolean missionAccomplished();   
    int newPlayerLevel();
    */

    boolean getPlayerCash();

    String getItemName(Item passedItem);
    
    double getItemPrice(Item passedItem);

    String getTypeName(Item passedItem);

    void getDescription(Item passedItem);
}
