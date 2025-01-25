package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

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
}
