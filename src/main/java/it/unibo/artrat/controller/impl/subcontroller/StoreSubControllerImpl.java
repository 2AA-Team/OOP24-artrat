package it.unibo.artrat.controller.impl.subcontroller;

import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.inventory.Item;

/**
 * implementation of the sub controller for the store.
 */
public class StoreSubControllerImpl extends MainControllerImpl.AbstractSubController implements StoreSubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController
     */
    public StoreSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }

    @Override
    public List<Item> purchasableItems() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'purchasableItems'");
    }

    @Override
    public boolean buyItem(Item itemToBuy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    }

    @Override
    public boolean getPlayerCash(){
        throw new UnsupportedOperationException("Unimplemented method");
    }
    /**
     * 
     * @param passedItem the item that we want the typeName.
     * @return the type name of desired item.
     */
    @Override
    public String getTypeName(Item passedItem){
        throw new UnsupportedOperationException("");
    }

    /**
     * 
     * @param passedItem the item that we want the typeName.
     */
    @Override
    public void getDescription(Item passedItem){
        throw new UnsupportedOperationException("");
    }
}
