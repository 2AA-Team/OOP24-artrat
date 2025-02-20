package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;

/**
 * A specific item.
 */
public class MysteriousWand extends AbstractItem {

    private final String nameOfItem;

     /**
     * A constructor to initialize the new item Mysterious Staff.
     * @param desc the description of Mysterious Staff.
     * @param price the price of Mysterious Staff.
     * @param itemType the item type of Mysterious Staff.
     */
    public MysteriousWand(final String desc, final double price, final ItemType itemType) {
        super(desc, price, itemType);
        nameOfItem = this.getClass().getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        final Inventory inventory = player.getInventory();
        inventory.getStoredItem().stream().filter(x -> !x.getClass().getSimpleName().equals(nameOfItem))
                                            .forEach(inventory::addItem);
        player.setInventory(new InventoryImpl(inventory));
        return player;
    }
}
