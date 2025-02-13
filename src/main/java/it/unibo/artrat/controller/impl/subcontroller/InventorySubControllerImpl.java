package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.characters.PlayerImpl;
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
        return new ArrayList<>(this.getModel().getPlayer().getInventory().getStoredItem());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean useItem(final Item passedItem) {
        //The idea is to obtain the current model, modify the desired parameter
        //and then update the centralized model in the main controller.
        final Model model = this.getModel();
        final Player player = model.getPlayer();
        final Inventory inv = player.getInventory();
        if (inv.useItem(passedItem)) {
            player.setInventory(inv);
            final Player modifiedPlayer = passedItem.consume(new PlayerImpl(player));
            model.setPlayer(new PlayerImpl(modifiedPlayer));
            this.updateCentralizeModel(new ModelImpl(model));
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getItemName(Item passedItem) {
        return passedItem.getClass().getSimpleName();
    }

    private String getTypeName(Item passedItem) {
        return this.getModel().getPlayer().getInventory().getStoredItem().stream().filter(x -> x.equals(passedItem))
        .map(x -> x.getType().name()).findAny().get();
    }

      /**
     * {@inheritDoc}
     */
    @Override
    public void obtainDescription(Item passedItem) {
        this.inventoryView.displayMessage(this.getModel().getPlayer().getInventory().getStoredItem().stream()
        .filter(x -> x.equals(passedItem)).map(Item::getDescription).findAny().get() + "\nTYPE: " +getTypeName(passedItem), "Descrizione Oggetto");
    }

}
