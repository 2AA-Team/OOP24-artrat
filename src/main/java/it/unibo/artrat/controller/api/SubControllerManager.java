package it.unibo.artrat.controller.api;

import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;

/**
 * interface for a class that manage all subcontroller.
 */
public interface SubControllerManager {
    /**
     * return controller for the effective game.
     * 
     * @return subController for the game
     */
    GameSubController getGameSubController();

    /**
     * return controller for the seguent model: Menu.
     * 
     * @return subController for the Menu
     */
    MenuSubController getMenuSubController();

    /**
     * return controller for the seguent model: Inventory.
     * 
     * @return subController for the Inventory
     */
    InventorySubController getInventorySubController();

    /**
     * return controller for the seguent model: Store.
     * 
     * @return subController for the Store
     */
    StoreSubController getStoreSubController();
}
