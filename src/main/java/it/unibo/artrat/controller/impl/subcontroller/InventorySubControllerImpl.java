package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

/**
 * implementation of the sub controller for the inventory.
 */
public class InventorySubControllerImpl extends AbstractSubController
        implements InventorySubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController main controller
     */
    public InventorySubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }
}
