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

    private static final String MULTIPLIER_BOOSTER = "MULTIPLIERBOOSTER";
    private static final String LUCKY_TICKET = "LUCKYTICKET";
    private static final String MAGIC_BACKPACK = "MAGICBACKPACK";
    private static final String MYSTERIOUS_WAND = "MYSTERIOUSWAND";
    private static final String WINGED_BOOTS = "WINGEDBOOTS";
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
                this.itemReader.setPath(itemPath.toURI());
            } catch (IOException | URISyntaxException e) {
                LOGGER.error("Item reader thown an error : ", e);
            }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item multiplierBooster() {
        return new MultiplierBooster(itemReader.getName(MULTIPLIER_BOOSTER),
                itemReader.getDescription(MULTIPLIER_BOOSTER),
                itemReader.getPrice(MULTIPLIER_BOOSTER),
                itemReader.getItemType(MULTIPLIER_BOOSTER));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item luckyTicket() {
        return new LuckyTicket(itemReader.getName(LUCKY_TICKET),
            itemReader.getDescription(LUCKY_TICKET),
            itemReader.getPrice(LUCKY_TICKET),
            itemReader.getItemType(LUCKY_TICKET));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item magicbackpack() {
        return new MagicBackpack(itemReader.getName(MAGIC_BACKPACK),
            itemReader.getDescription(MAGIC_BACKPACK),
            itemReader.getPrice(MAGIC_BACKPACK),
            itemReader.getItemType(MAGIC_BACKPACK));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item mysteriouswand() {
        return new MysteriousWand(itemReader.getName(MYSTERIOUS_WAND), 
            itemReader.getDescription(MYSTERIOUS_WAND), 
            itemReader.getPrice(MYSTERIOUS_WAND), 
            itemReader.getItemType(MYSTERIOUS_WAND));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item wingedboots() {
        return new WingedBoots(itemReader.getName(WINGED_BOOTS), 
            itemReader.getDescription(WINGED_BOOTS), 
            itemReader.getPrice(WINGED_BOOTS), 
            itemReader.getItemType(WINGED_BOOTS));
    }
}
