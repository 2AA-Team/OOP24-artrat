package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.api.inventory.ItemType;
/**
 * A specific Item.
 */
public class LuckyTicket extends AbstractItem {

     /**
     * A constructor to initialize the new item Lucky Ticket.
     * @param desc the description of Lucky Ticket.
     * @param price the price of Lucky Ticket.
     * @param type the item type of Lucky Ticket.
     */
    public LuckyTicket(String desc, final double price, final ItemType type) {
        super(desc,price,type);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean consume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consume'");
    }
}
