package it.unibo.artrat.model.api.inventory;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Class that rappresents shop/inventory item.
 * @author Cristian Di Donato.
 */
public interface Item {

    /**
     * 
     * @return String that rappresent the name of object: Effect, Story, Curiosity.
     */
    String getName();

    /**
     * 
     * @return String that rappresent the description of object: Effect, Story, Curiosity.
     */
    String getDescription();

    /**
     * 
     * @return the price of the item.
     */
    double getPrice();

    /**
     * 
     * @return the ItemType of the current Item: can be Consumable or PowerUp.
     */
    ItemType getType();

    /**
     * @param player the player where the item operate.
     * @return true: if the item is consume; false: if the item can't be consume for some reason.
     */
    Player consume(Player player);


}
