package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.impl.inventory.ItemType;

public class LuckyTicket extends AbstractItem{

    public LuckyTicket(String desc, final double price, final ItemType type) {
        super(desc,price,type);
    }

    @Override
    public boolean consume() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consume'");
    }
}
