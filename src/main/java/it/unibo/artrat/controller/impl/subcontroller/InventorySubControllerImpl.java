package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.view.api.InventoryView;
import it.unibo.artrat.view.impl.InventorySubPanel;

/**
 * implementation of the sub controller for the inventory.
 */
public class InventorySubControllerImpl extends AbstractSubController
        implements InventorySubController {

        private final InventoryView inventoryView;


    /**
     * constructor to initialize mainController.
     * 
     * @param mainController main controller
     */
    public InventorySubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
        this.inventoryView = new InventorySubPanel(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getStoredItem() {
        return new ArrayList<>(this.getModel().getInventory().getStoredItem());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useItem(Item passedItem) {
        Inventory inv = this.getModel().getInventory();
        if(inv.useItem(passedItem)) {
            this.getModel().setInventory(inv);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName(Item passedItem) {
        return super.getModel().getInventory().getStoredItem().stream().filter(x -> x.equals(passedItem)).map(x -> x.getType().name()).findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void getDescription(Item passedItem) {
        this.inventoryView.displayMessage(super.getModel().getInventory().getStoredItem().stream().filter(x -> x.equals(passedItem)).map(x -> x.getDescription()).findAny().get(), "Descrizione Oggetto");
    }

}
