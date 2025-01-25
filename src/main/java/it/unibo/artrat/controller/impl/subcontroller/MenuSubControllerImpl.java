package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

/**
 * implementation of the sub controller for the menu.
 */
public class MenuSubControllerImpl extends MainControllerImpl.AbstractSubController implements MenuSubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController
     */
    public MenuSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }
}
