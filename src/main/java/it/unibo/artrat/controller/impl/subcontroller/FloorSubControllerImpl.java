package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.FloorSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

/**
 * implementation of the sub controller for the floor.
 */
public class FloorSubControllerImpl extends MainControllerImpl.AbstractSubController
        implements FloorSubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController
     */
    public FloorSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }
}
