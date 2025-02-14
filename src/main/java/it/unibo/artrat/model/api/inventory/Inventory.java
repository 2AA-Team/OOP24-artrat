package it.unibo.artrat.model.api.inventory;

import java.util.List;

/**
 * Class that rappresents entity's inventory.
 * @author Cristian Di Donato.
 */
public interface Inventory {

    /**
     * 
     * @return a list of all element that player have.
     */
    List<Item> getStoredItem();

   /**
    * 
    * @param newItem is the new item to add to inventory.
    * @return true: if the item can be add correctly. false: if the item can't be add.
    */
    boolean addItem(Item newItem);

    /**
     * 
     * @param itemToUse is the item that player want to use.
     * @return true: if the item is used correctly. false: if the item can't be used.
     */
    boolean useItem(Item itemToUse);

}
