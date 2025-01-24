package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

public class InventorySubControllerImpl extends MainControllerImpl.AbstractSubController
        implements InventorySubController {

    public InventorySubControllerImpl(MainControllerImpl mainController) {
        mainController.super();
    }
}
