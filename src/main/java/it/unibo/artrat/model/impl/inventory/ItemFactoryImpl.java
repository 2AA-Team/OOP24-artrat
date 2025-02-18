package it.unibo.artrat.model.impl.inventory;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemFactory;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MagicBackpack;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;
import it.unibo.artrat.model.impl.inventory.items.MysteriousStaff;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
 * An implementation of ItemFactory.
 */
public class ItemFactoryImpl implements ItemFactory {

    private final String itemPath = "src" + File.separator
            + "main" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "resources" + File.separator
            + "items" + File.separator
            + "items.yaml";

    private final ItemReader itemReader;
    private static final java.util.logging.Logger LOGGER = LoggerFactory.getLogger(SingleThreadedGame.class);

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
    public void initialize() throws IOException {
        this.itemReader.readFromItemFile(itemPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item multiplierBooster() {
        LOGGER.info("Request for creation of multiplier booster");
        return new MultiplierBooster(itemReader.getDescription("MULTIPLIERBOOSTER"), 
            itemReader.getPrice("MULTIPLIERBOOSTER"), 
            itemReader.getItemType("MULTIPLIERBOOSTER"));
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Item luckyTicket() {
        LOGGER.info("Request for creation of lucky ticket");
        return new LuckyTicket(itemReader.getDescription("LUCKYTICKET"), 
            itemReader.getPrice("LUCKYTICKET"), 
            itemReader.getItemType("LUCKYTICKET"));
        }

    /**
     * {@inheritDoc}
    */
    @Override
    public Item magicbackpack() {
        LOGGER.info("Request for creation of magic backpack");
        return new MagicBackpack(itemReader.getDescription("MAGICBACKPACK"), 
        itemReader.getPrice("MAGICBACKPACK"), 
        itemReader.getItemType("MAGICBACKPACK"));
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Item mysterioustaff() {
        LOGGER.info("Request for creation of mysterious staff");
        return new MysteriousStaff(itemReader.getDescription("MYSTERIOUSSTAFF"), 
        itemReader.getPrice("MYSTERIOUSSTAFF"), 
        itemReader.getItemType("MYSTERIOUSSTAFF"));
    }
}
