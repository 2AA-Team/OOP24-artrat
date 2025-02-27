package it.unibo.artrat.model.api.inventory;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Class that rappresents shop/inventory item.
 * 
 * @author Cristian Di Donato.
 */
public interface Item {

    /**
     * Method to get the item's name.
     * 
     * @return String that rappresent the name of item.
     */
    String getName();

    /**
     * Method to get the item's description.
     * 
     * @return String that rappresent the description of item.
     */
    String getDescription();

    /**
     * Method to get the item's price.
     * 
     * @return the price of the item.
     */
    double getPrice();

    /**
     * Method to get the item's itemType.
     * 
     * @return the ItemType of the current Item: can be Consumable or PowerUp.
     */
    ItemType getType();

    /**
     * Method to use the item on the given player.
     * 
     * @param player the player where the item operate.
     * @return A new player with the applied modifications from using the current
     *         item.
     */
    Player consume(Player player);
}
