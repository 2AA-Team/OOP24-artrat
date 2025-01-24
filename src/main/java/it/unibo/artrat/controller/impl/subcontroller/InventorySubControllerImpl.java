package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

/**
 * implementation of the sub controller for the inventory.
 */
public class InventorySubControllerImpl extends MainControllerImpl.AbstractSubController
        implements InventorySubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController
     */
    public InventorySubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }
}
