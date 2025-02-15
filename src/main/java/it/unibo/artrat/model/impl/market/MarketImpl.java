package it.unibo.artrat.model.impl.market;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.utils.api.ItemReader;

public class MarketImpl implements Market{    
    private final String itemPath = "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "resources" + File.separator
            + "items" + File.separator
            + "items.yaml";


    private final List<Item> itemsToBuy;
    private final String descr;
    private final double price;
    private final ItemType type;

    public MarketImpl(){
        this.itemsToBuy = new ArrayList<>();
    }

    public MarketImpl(ItemReader itemReader) throws IOException {
        this.itemsToBuy = new ArrayList<>();
        itemReader.readFromItemFile(itemPath);
        for (String itemName : itemReader.getList()){
            this.descr = itemReader.getDescription(itemName);
            this.price = itemReader.getPrice(itemName);
            this.type = itemReader.getItemType(itemName);

            final Item item = new Item(descr, price, type);
            this.itemsToBuy.add(item);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getPurchItems(){
        return new ArrayList<>(itemsToBuy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(Item passedItem){
        if(itemsToBuy.contains(passedItem)){
            if(passedItem.getType() == ItemType.POWERUP){
                itemsToBuy.remove(passedItem);
                return true;
            }
        }
        return false;
    }
}
