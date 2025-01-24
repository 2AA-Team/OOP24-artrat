package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

public class StoreSubControllerImpl extends MainControllerImpl.AbstractSubController implements StoreSubController {

    public StoreSubControllerImpl(MainControllerImpl mainController) {
        mainController.super();
    }
}
