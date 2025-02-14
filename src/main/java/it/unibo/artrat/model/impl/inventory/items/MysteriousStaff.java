package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;

/**
 * A specific item.
 */
public class MysteriousStaff extends AbstractItem {

     /**
     * A constructor to initialize the new item Mysterious Staff.
     * @param desc the description of Mysterious Staff.
     * @param price the price of Mysterious Staff.
     * @param itemType the item type of Mysterious Staff.
     */
    public MysteriousStaff(final String desc, final double price, final ItemType itemType) {
        super(desc, price, itemType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        final Inventory inventory = player.getInventory();
        inventory.getStoredItem().stream().forEach(inventory::addItem);
        player.setInventory(new InventoryImpl(inventory));
        return player;
    }
}
