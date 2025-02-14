package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.Market;

public class MarketImpl implements Market{    
    private final List<Item> itemsToBuy;
    
    public MarketImpl(){
        this.itemsToBuy = new ArrayList<>();
    }

    public MarketImpl(final Market itemsToBuy){
        this.itemsToBuy = itemsToBuy.getPurchItems();
    }

    @Override
    public List<Item> getPurchItems(){
        return new ArrayList<>(itemsToBuy);
    }

    @Override
    public boolean buyItem(Item passedItem){
        final Player player = ;

        if(itemsToBuy.contains(passedItem) && player.getCoin().getCurrentAmount() >= passedItem.getPrice()){
            player.getCoin().spendCoins(passedItem.getPrice());
            if(passedItem.getType() == ItemType.POWERUP){
                itemsToBuy.remove(passedItem);
            }
            return true;
        }
        return false;
    }
}
