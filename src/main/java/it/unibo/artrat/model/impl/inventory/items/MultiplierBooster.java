package it.unibo.artrat.model.impl.inventory.items;

import it.unibo.artrat.model.impl.inventory.AbstractItem;
import it.unibo.artrat.model.api.inventory.ItemType;;;

public class MultiplierBooster extends AbstractItem{

    /**
     * A constructor to initialize the new item Multiplier Booster.
     * @param desc the description of Multiplier Booster
     * @param price the price of Multiplier Booster
     * @param type the item type of Multiplier Booster
     */
    public MultiplierBooster(String desc, final double price, final ItemType type) {
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
