package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;

/**
 * A specific item that completely clears the player's inventory once used.
 * 
 * @author Cristian Di Donato.
 */
public class MagicBackpack extends AbstractItem {

    /**
     * A constructor to initialize the new item MagicBackpack.
     * 
     * @param name     the name of Magic Backpack.
     * @param desc     the description of Magic Backpack.
     * @param price    the price of Magic Backpack.
     * @param itemType the item type of Magic Backpack.
     */
    public MagicBackpack(final String name, final String desc, final double price, final ItemType itemType) {
        super(name, desc, price, itemType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        final Inventory inventory = player.getInventory();
        inventory.getStoredItem().stream().forEach(inventory::useItem);
        player.setInventory(new InventoryImpl(inventory));
        return player;
    }
}
