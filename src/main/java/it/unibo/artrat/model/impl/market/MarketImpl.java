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

/**
 * Market implementation on the Model.
 */
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

    /**
     * 
     */
    public MarketImpl() {
        this.itemsToBuy = new ArrayList<>();
        this.itemReader = new ItemReaderImpl();
        this.itemFactory = new ItemFactoryImpl();
        try {
            initMarket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * MarketImpl constructor.
     * @param mark a market object
     */
    public MarketImpl(final Market mark) {
        this.itemsToBuy = new ArrayList<>();
        this.itemsToBuy.addAll(mark.getPurchItems());
        this.itemReader = new ItemReaderImpl();
        this.itemFactory = new ItemFactoryImpl();
    }

    /**
     * this method uses ItemReaderImpl to read my yaml file items.yaml.
     * It adds my items (created using the private method createItem) in my list.
     * @throws IOException
     */
    public void initMarket() throws IOException {
        this.itemReader.readFromItemFile(itemPath);
        this.itemFactory.initialize();
        for (final String it : itemReader.getAllItemsName()) {
            this.itemsToBuy.add(createItem(it));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> getPurchItems() {
        return new ArrayList<>(itemsToBuy);
    }

    /**
     * Update my list of items, it's useful for ItemManager.
     */
    @Override
    public void setPurchItems(final List<Item> items) {
        this.itemsToBuy = new ArrayList<>(items);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(final Item passedItem) {
        if (itemsToBuy.contains(passedItem)) {
            if (passedItem.getType() == ItemType.POWERUP) {
                itemsToBuy.remove(passedItem);
            }
            return true;
        }
        return false;
    }

    /**
     * This private method has made to create my items and testing if I am correctly reading from my items.yaml file.
     * 
     * @param nameItem the name of the item which it has to be created
     * @return the item created using itemFactory
     * @throws IllegalArgumentException if my passed item name is not compatible
     */
    private Item createItem(final String nameItem) {
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
