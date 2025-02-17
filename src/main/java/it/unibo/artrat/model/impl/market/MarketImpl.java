package it.unibo.artrat.model.impl.market;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.inventory.ItemFactoryImpl;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

public class MarketImpl implements Market {    
    private final String itemPath = "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "resources" + File.separator
            + "items" + File.separator
            + "items.yaml";

    private List<Item> itemsToBuy;
    private final ItemReader itemReader;
    private final ItemFactoryImpl itemFactory;

    public MarketImpl(){
        this.itemsToBuy = new ArrayList<>();
        this.itemReader = new ItemReaderImpl();
        this.itemFactory = new ItemFactoryImpl();
        try {
            initMarket();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public MarketImpl(Market mark) {
        this.itemsToBuy = new ArrayList<>();
        this.itemsToBuy.addAll(mark.getPurchItems());
        this.itemReader = new ItemReaderImpl();
        this.itemFactory = new ItemFactoryImpl();
    }

    public void initMarket() throws IOException {
        this.itemReader.readFromItemFile(itemPath);
        this.itemFactory.initialize();
        for (String it : itemReader.getAllItemsName()) {
            this.itemsToBuy.add(createItem(it));
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
     *
     */
    @Override
    public void setPurchItems(List<Item> items) {       //aggiorno la mia lista di elementi (filtraggio, sorting)
        this.itemsToBuy = new ArrayList<>(items);
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

    private Item createItem(String nameItem) {
        switch (nameItem) {
            case "MULTIPLIERBOOSTER":
                return itemFactory.multiplierBooster();
            case "LUCKYTICKET":
                return itemFactory.luckyTicket();
            case "MAGICBACKPACK":
                return itemFactory.magicbackpack();
            case "MYSTERIOUSTAFF":
                return itemFactory.mysterioustaff();
            default:
                break;
        }
        throw new IllegalArgumentException("The passed item name is not compatible");
    }
}
