package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.market.Market;

public class MarketImpl implements Market{    
    private final List<Item> itemsToBuy;

    public MarketImpl(){
        this.itemsToBuy = new ArrayList<>();
    }

    public MarketImpl(final Market itemsToBuy ){
        this.itemsToBuy = itemsToBuy.getPurchItems();
    }

    @Override
    public List<Item> getPurchItems(){
        
        throw new UnsupportedOperationException("Unimplemented method 'getPurchItems'");
    }

    @Override
    public boolean buyItem(Item passedItem){

        throw new UnsupportedOperationException("Unimplemented method 'buyItem'");
    }
}
