package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.inventory.AbstractItem;

/**
 * An implementation of WingedBoots item.
 */
public class WingedBoots extends AbstractItem {

    private static final double MAX_BOOST = 3.0;
    private static final double ADD_BOOST_UNIT = 0.2;

    /**
     * A constructor to initialize the new item Winged Boots.
     * @param desc the description of Winged Boots
     * @param price the price of Winged Boots
     * @param itemType the item type of Winged Boots
     */
    public WingedBoots(final String desc, final double price, final ItemType itemType) {
        super(desc, price, itemType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        if (player.getBoost() + ADD_BOOST_UNIT <= MAX_BOOST) {
            player.setBoost(player.getBoost() + ADD_BOOST_UNIT);
        }
        return player.copyPlayer();
    }
}
