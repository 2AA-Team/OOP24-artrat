package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.impl.inventory.AbstractItem;

import java.util.Random;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.ItemType;

/**
 * A specific item, which each time it is used, increases the player's current multiplier 
 * up to a maximum of MAX_MULTIPLIER_INCREASE.
 * @author Cristian Di Donato.
 */
public class MultiplierBooster extends AbstractItem {

    private static final int MAX_MULTIPLIER_INCREASE = 3;
    private final Random rd;

    /**
     * A constructor to initialize the new item Multiplier Booster.
     * 
     * @param name  the name of Multiplier Booster.
     * @param desc  the description of Multiplier Booster
     * @param price the price of Multiplier Booster
     * @param type  the item type of Multiplier Booster
     */
    public MultiplierBooster(final String name, final String desc, final double price, final ItemType type) {
        super(name, desc, price, type);
        this.rd = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        player.increaseMultiplier(
                1.0 + player.getMultiplier().getCurrentMultiplier() + rd.nextInt(MAX_MULTIPLIER_INCREASE));
        return player.copyPlayer();
    }
}
