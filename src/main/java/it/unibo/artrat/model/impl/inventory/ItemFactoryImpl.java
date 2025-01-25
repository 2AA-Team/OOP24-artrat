package it.unibo.artrat.model.impl.inventory;

import java.io.File;
import java.io.IOException;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemFactory;
import it.unibo.artrat.model.impl.inventory.items.LuckyTicket;
import it.unibo.artrat.model.impl.inventory.items.MultiplierBooster;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

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

    /**
     * A constructor to initialize itemReader.
     */
    public ItemFactoryImpl() {
        this.itemReader = new ItemReaderImpl();
    }

    /**
     * {@inheritDoc}
     */
    public void initialize() throws IOException {
        this.itemReader.readFromItemFile(itemPath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item multiplierBooster() {
        return new MultiplierBooster(itemReader.getDescription("MULTIPLIERBOOSTER"), itemReader.getPrice("MULTIPLIERBOOSTER"), itemReader.getItemType("MULTIPLIERBOOSTER"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item luckyTicket() {
        return new LuckyTicket(itemReader.getDescription("LUCKYTICKET"), itemReader.getPrice("LUCKYTICKET"), itemReader.getItemType("LUCKYTICKET"));
    }
    
}
