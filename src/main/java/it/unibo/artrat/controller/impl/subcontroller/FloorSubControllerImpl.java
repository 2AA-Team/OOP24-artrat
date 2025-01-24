package it.unibo.artrat.controller.impl.subcontroller;

import it.unibo.artrat.controller.api.subcontroller.FloorSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;

public class FloorSubControllerImpl extends MainControllerImpl.AbstractSubController
        implements FloorSubController {

    public FloorSubControllerImpl(MainControllerImpl mainController) {
        mainController.super();
    }
}
