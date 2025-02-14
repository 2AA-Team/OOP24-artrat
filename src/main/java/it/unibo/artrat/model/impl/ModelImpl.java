package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.characters.PlayerImpl;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MagicBackpack;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;
import it.unibo.artrat.model.impl.inventory.items.MysteriousStaff;
import it.unibo.artrat.model.impl.market.MarketImpl;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {

    private Player player;
    private Market market;

    public ModelImpl() {
        this.player = new PlayerImpl();
        this.market = new MarketImpl();
        initInventory(); // temporaneo per i test, andrà tolto in quanto all'inizio l'inventario sarà vuoto.
    }

    public ModelImpl(final Model m) {
        this.player = m.getPlayer();
        this.market = m.getMarket();
    }
    
    private void initInventory() {
        final Inventory inv = this.player.getInventory();
        inv.addItem(new LuckyTicket("LuckyTicket", 0, ItemType.POWERUP));
        inv.addItem(new MultiplierBooster("MultiplierBooster", 0, ItemType.POWERUP));
        inv.addItem(new MagicBackpack("MagicBackpack", 0, ItemType.CONSUMABLE));
        inv.addItem(new MysteriousStaff("MysteriousStaff", 0, ItemType.CONSUMABLE));
        inv.addItem(new MysteriousStaff("MysteriousStaff", 0, ItemType.CONSUMABLE));
        this.player.setInventory(new InventoryImpl(inv));
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Player getPlayer() {
        return new PlayerImpl(player);
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public void setPlayer(final Player player) {
        this.player = new PlayerImpl(player);
    }

    /**
     * {@inheritDoc}
     * @author Manuel Benagli
     */
    @Override
    public Market getMarket() {
        return new MarketImpl(market);
    }

    /**
     * {@inheritDoc}
     * @autor Manuel Benagli
     */
    @Override
    public void setMarket(Market market) {
        this.market = new MarketImpl(market);
    }
}
