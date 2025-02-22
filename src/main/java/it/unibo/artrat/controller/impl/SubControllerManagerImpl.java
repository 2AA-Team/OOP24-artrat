package it.unibo.artrat.controller.impl;

import java.io.IOException;

import java.util.Objects;

import it.unibo.artrat.controller.api.SubControllerManager;
import it.unibo.artrat.controller.api.subcontroller.GameSubController;
import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.api.subcontroller.MenuSubController;
import it.unibo.artrat.controller.api.subcontroller.MissionSubController;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.subcontroller.GameSubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.InventorySubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.MenuSubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.MissionSubControllerImpl;
import it.unibo.artrat.controller.impl.subcontroller.StoreSubControllerImpl;
import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * implementation of SubControllerManager.
 */
public class SubControllerManagerImpl implements SubControllerManager {
    private final MenuSubController menuSubController;
    private final GameSubController gameSubController;
    private final InventorySubController inventorySubController;
    private final StoreSubController storeSubController;
    private final MissionSubController missionSubController;

    /**
     * constructor that define all subController.
     * 
     * @param mainController main controller
     * @param rl             resource loader for configuration purpose
     * @throws IOException If the resource loader has issues reading the file
     */
    public SubControllerManagerImpl(final MainControllerImpl mainController, final ResourceLoader<String, Double> rl)
            throws IOException {
        this.gameSubController = new GameSubControllerImpl(mainController, rl);
        this.menuSubController = new MenuSubControllerImpl(mainController);
        this.inventorySubController = new InventorySubControllerImpl(mainController);
        this.storeSubController = new StoreSubControllerImpl(mainController);
        this.missionSubController = new MissionSubControllerImpl(mainController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameSubController getGameSubController() {
        final var help = Objects.requireNonNull(this.gameSubController);
        help.init();
        return help;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MenuSubController getMenuSubController() {

        return Objects.requireNonNull(this.menuSubController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventorySubController getInventorySubController() {
        return Objects.requireNonNull(this.inventorySubController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StoreSubController getStoreSubController() {
        return Objects.requireNonNull(this.storeSubController);
    }

    @Override
    public MissionSubController getMissionsSubController() {
        return Objects.requireNonNull(this.missionSubController);
    }
}
