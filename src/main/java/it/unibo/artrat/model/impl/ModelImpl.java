package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MagicBackpack;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;
import it.unibo.artrat.model.impl.inventory.items.MysteriousStaff;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {

    private Player player;

    /**
     * Permit to create a new istance of Model.
     */
    public ModelImpl() {
        this.player = new Lupino(new Point(), new Point());
        initInventory(); // temporaneo per i test, andrà tolto in quanto all'inizio l'inventario sarà vuoto.
    }

    /**
     * Permit to create a new istance of model, starting from the passed one.
     * @param m the passed Model.
     */
    public ModelImpl(final Model m) {
        this.player = m.getPlayer();
    }

    private void initInventory() {
        final Inventory inv = this.player.getInventory();
        inv.addItem(new LuckyTicket("LuckyTicket", 0, ItemType.POWERUP));
        inv.addItem(new MultiplierBooster("MultiplierBooster", 0, ItemType.POWERUP));
        inv.addItem(new MagicBackpack("MagicBackpack", 0, ItemType.CONSUMABLE));
        inv.addItem(new MysteriousStaff("MysteriousStaff", 0, ItemType.CONSUMABLE));
        this.player.setInventory(new InventoryImpl(inv));
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Player getPlayer() {
        return this.player.copyPlayer();
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public void setPlayer(final Player player) {
        this.player = player.copyPlayer();
    }
}
