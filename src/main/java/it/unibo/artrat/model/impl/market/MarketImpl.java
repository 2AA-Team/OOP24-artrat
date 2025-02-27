package it.unibo.artrat.model.impl.market;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.inventory.ItemFactoryImpl;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
 * Shop implementation model class.
 * 
 * @author Manuel Benagli
 */
public class MarketImpl implements Market {
    private final InputStream itemPath = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "items/items.yaml");

    private List<Item> itemsToBuy;
    private final ItemFactoryImpl itemFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketImpl.class);

    /**
     * Shop default constructor.
     */
    public MarketImpl() {
        this.itemsToBuy = new ArrayList<>();
        this.itemFactory = new ItemFactoryImpl();
    }

    /**
     * Shop constructor.
     * 
     * @param mark a shop object
     */
    public MarketImpl(final Market mark) {
        this.itemsToBuy = new ArrayList<>();
        this.itemsToBuy.addAll(mark.getPurchItems());
        this.itemFactory = new ItemFactoryImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initMarket() {
        final ItemReader itemReader = new ItemReaderImpl();
        try {
            itemReader.setPath(itemPath);
            this.itemFactory.initialize();
        } catch (IOException e) {
            LOGGER.error("MarketImpl class thrown an error : ", e);
        }
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
     * {@inheritDoc}
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
     * This private method has made to create my items using itemFactory.
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
            case "MYSTERIOUSWAND":
                return itemFactory.mysteriouswand();
            case "WINGEDBOOTS":
                return itemFactory.wingedboots();
            default:
                break;
        }
        throw new IllegalArgumentException("The passed item name is not compatible");
    }
}
