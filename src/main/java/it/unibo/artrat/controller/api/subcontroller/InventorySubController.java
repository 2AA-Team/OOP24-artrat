package it.unibo.artrat.controller.api.subcontroller;

import java.util.List;

import it.unibo.artrat.controller.api.SubController;
import it.unibo.artrat.model.api.inventory.Item;;

/**
 * Controller for the seguent model: inventory.
 */
public interface InventorySubController extends SubController{

    /**
     * 
     * @return to the view a list of all element that player have.
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
     * @param passedItem the item that we want the typeName.
     * @return the type name of desired item.
     */
    String getTypeName(Item passedItem);

    /**
     * 
     * @param passedItem the item that we want the typeName.
     */
    void getDescription(Item passedItem);

    //void quit();

}
