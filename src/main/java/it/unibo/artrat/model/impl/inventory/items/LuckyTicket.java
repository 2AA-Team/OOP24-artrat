package it.unibo.artrat.model.impl.inventory.items;

import java.util.Random;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.inventory.AbstractItem;

/**
 * A specific item that allows to win and add up to the MAX_WIN to the player's
 * money.
 * 
 * @author Cristian Di Donato.
 */
public class LuckyTicket extends AbstractItem {

    private final Random rd;
    private static final int MAX_WIN = 1000;

    /**
     * A constructor to initialize the new item Lucky Ticket.
     * 
     * @param name  the name of Lucky Ticket.
     * @param desc  the description of Lucky Ticket.
     * @param price the price of Lucky Ticket.
     * @param type  the item type of Lucky Ticket.
     */
    public LuckyTicket(final String name, final String desc, final double price, final ItemType type) {
        super(name, desc, price, type);
        this.rd = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        player.increaseCoins(rd.nextInt(MAX_WIN));
        return player.copyPlayer();
    }
}
