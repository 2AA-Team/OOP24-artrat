package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.characters.PlayerImpl;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;

public class ModelImpl implements Model{

    Inventory inventory;
    Player player;

    public ModelImpl() {
        this.inventory = new InventoryImpl();
        this.player = new PlayerImpl();
        this.inventory.addItem(new LuckyTicket("1", 0, ItemType.CONSUMABLE));
        this.inventory.addItem(new MultiplierBooster("2", 0, ItemType.POWERUP));
    }

    public ModelImpl(final Model m) {
        System.out.println(m.getInventory().getStoredItem());
        this.inventory = m.getInventory();
        System.out.println(this.inventory.getStoredItem());
        this.player = m.getPlayer();
    }


    @Override
    public Inventory getInventory() {
        return new InventoryImpl(inventory);
    }

    @Override
    public void setInventory(final Inventory inv) {
        this.inventory = inv;
    }

    @Override
    public Player getPlayer() {
        return new PlayerImpl(player);
    }

    @Override
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
}
