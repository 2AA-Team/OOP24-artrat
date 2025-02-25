package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * Controller for the seguent model: inventory.
 */
public interface InventorySubController extends SubController {

    /**
     * 
     * @return the list of all element that player have.
     */
    List<Item> getStoredItem();

     /**
     * 
     * @param passedItem the item that player want to use.
     * @return true: if the item is used correctly. false: if the item can't be used.
     */
    boolean useItem(Item passedItem);

      /**
       * 
       * @param passedItem the element whose name we want.
       * @return the name of desired item.
       */
    String getItemName(Item passedItem);

      /**
       * 
       * @param passedItem the element whose description we want.
       */
    void obtainDescription(Item passedItem);
}
