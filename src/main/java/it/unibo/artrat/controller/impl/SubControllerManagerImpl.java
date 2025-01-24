package it.unibo.artrat.controller.impl;

import it.unibo.artrat.controller.api.SubControllerManager;
import it.unibo.artrat.controller.api.subcontroller.FloorSubController;
import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.subcontroller.FloorSubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.InventorySubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.MenuSubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.StoreSubControllerImpl;

/**
 * {@inheritDoc}
 */
public class SubControllerManagerImpl implements SubControllerManager {

    private final MenuSubController menuSubController;
    private final FloorSubController floorSubController;
    private final InventorySubController inventorySubController;
    private final StoreSubController storeSubController;

    /**
     * constructor that define all subController
     * 
     * @param mainController
     */
    public SubControllerManagerImpl(MainControllerImpl mainController) {
        this.menuSubController = new MenuSubControllerImpl(mainController);
        this.floorSubController = new FloorSubControllerImpl(mainController);
        this.inventorySubController = new InventorySubControllerImpl(mainController);
        this.storeSubController = new StoreSubControllerImpl(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FloorSubController getFloorSubController() {
        return floorSubController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuSubController getMenuSubController() {
        return this.menuSubController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventorySubController getInventorySubController() {
        return inventorySubController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreSubController getStoreSubController() {
        return storeSubController;
    }

}
