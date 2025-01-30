package it.unibo.artrat.controller.impl.subcontroller;

import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.view.api.InventoryView;

/**
 * implementation of the sub controller for the inventory.
 */
public class InventorySubControllerImpl extends MainControllerImpl.AbstractSubController
        implements InventorySubController {

        //private InventoryView inventoryView;

        //model

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController
     */
    public InventorySubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getStoredItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStoredItem'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useItem(Item passedItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'useItem'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName(Item passedItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTypeName'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDescription(Item passedItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDescription'");
    }
}
