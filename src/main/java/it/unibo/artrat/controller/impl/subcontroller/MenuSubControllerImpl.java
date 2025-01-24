package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

public class MenuSubControllerImpl extends MainControllerImpl.AbstractSubController implements MenuSubController {

    public MenuSubControllerImpl(MainControllerImpl mainController) {
        mainController.super();
    }
}
