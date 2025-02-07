package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.impl.characters.PlayerImpl;
import it.unibo.artrat.model.impl.inventory.AbstractItem;

import java.util.Random;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.ItemType;
/**
 * A specific Item.
 */
public class LuckyTicket extends AbstractItem {

    private Random rd;

     /**
     * A constructor to initialize the new item Lucky Ticket.
     * @param desc the description of Lucky Ticket.
     * @param price the price of Lucky Ticket.
     * @param type the item type of Lucky Ticket.
     */
    public LuckyTicket(final String desc, final double price, final ItemType type) {
        super(desc, price, type);
        this.rd = new Random();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player consume(final Player player) {
        player.increaseCoins(rd.nextInt(1000));
        return new PlayerImpl(player);
    }
}
