package it.unibo.artrat.model.impl.inventory;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemFactory;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MagicBackpack;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;
import it.unibo.artrat.model.impl.inventory.items.MysteriousWand;
import it.unibo.artrat.model.impl.inventory.items.WingedBoots;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
 * An implementation of ItemFactory.
 */
public class ItemFactoryImpl implements ItemFactory {

    private final URL itemPath = Thread.currentThread().getContextClassLoader().getResource(
            "items" + File.separator
                    + "items.yaml");

    private final ItemReader itemReader;
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemFactoryImpl.class);

    /**
     * A constructor to initialize itemReader.
     */
    public ItemFactoryImpl() {
        this.itemReader = new ItemReaderImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
            try {
                this.itemReader.setItemPath(itemPath.toURI());
            } catch (IOException | URISyntaxException e) {
                LOGGER.error("Item reader thown an error : ", e);
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item multiplierBooster() {
        return new MultiplierBooster(itemReader.getDescription("MULTIPLIERBOOSTER"),
                itemReader.getPrice("MULTIPLIERBOOSTER"),
                itemReader.getItemType("MULTIPLIERBOOSTER"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item luckyTicket() {
        return new LuckyTicket(itemReader.getDescription("LUCKYTICKET"),
                itemReader.getPrice("LUCKYTICKET"),
                itemReader.getItemType("LUCKYTICKET"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item magicbackpack() {
        return new MagicBackpack(itemReader.getDescription("MAGICBACKPACK"),
                itemReader.getPrice("MAGICBACKPACK"),
                itemReader.getItemType("MAGICBACKPACK"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item mysteriouswand() {
        return new MysteriousWand(itemReader.getDescription("MYSTERIOUSWAND"), 
        itemReader.getPrice("MYSTERIOUSWAND"), 
        itemReader.getItemType("MYSTERIOUSWAND"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item wingedboots() {
        return new WingedBoots(itemReader.getDescription("WINGEDBOOTS"), 
        itemReader.getPrice("WINGEDBOOTS"), 
        itemReader.getItemType("WINGEDBOOTS"));
    }
}
