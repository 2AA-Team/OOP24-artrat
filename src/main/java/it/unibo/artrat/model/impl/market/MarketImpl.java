package it.unibo.artrat.model.impl.market;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
 * Market implementation model class.
 */
public class MarketImpl implements Market {
    private final URL itemPath = Thread.currentThread().getContextClassLoader().getResource(
            "items" + File.separator
                    + "items.yaml");

    private List<Item> itemsToBuy;
    private final ItemFactoryImpl itemFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketImpl.class);

    /**
     * Market default constructor.
     */
    public MarketImpl() {
        this.itemsToBuy = new ArrayList<>();
        this.itemFactory = new ItemFactoryImpl();
    }

    /**
     * Market constructor.
     * @param mark a market object
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
            itemReader.setPath(itemPath.toURI());
            this.itemFactory.initialize();
        } catch (IOException | URISyntaxException e) {
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
