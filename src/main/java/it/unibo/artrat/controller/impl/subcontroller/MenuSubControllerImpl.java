package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

/**
 * implementation of the sub controller for the menu.
 * 
 * @author Matteo Tonelli
 */
public class MenuSubControllerImpl extends AbstractSubController implements MenuSubController {

    /**
     * constructor to initialize mainController.
     * 
     * @param mainController main controller
     */
    public MenuSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
    }

}
