package it.unibo.artrat.utils.impl;

import java.io.IOException;
import java.util.List;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * An implementation of ItemReader.
 */
public class ItemReaderImpl implements ItemReader {
    /**
     * The actual reader from yaml
     */
    private final ResourceLoader<String,List<String>> valueOfYaml;


    /**
     * A constructor that initializes an instance of Resource Loader.
     */
    public ItemReaderImpl() {
        this.valueOfYaml = new ResourceLoaderImpl<>();
    }

    /**
     * {@inheritDoc}
     */
    public void readFromItemFile(final String itemPath) throws IOException {
        this.valueOfYaml.setConfigPath(itemPath);
    }

    private String getSpecificField(String nameOfItem, final int field) {
        return ((List<String>) (valueOfYaml.getConfig(nameOfItem))).get(field);
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(String nameOfItem) {
        return getSpecificField(nameOfItem, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(String nameOfItem) {
        return Double.parseDouble(getSpecificField(nameOfItem, 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(String nameOfItem) {
        switch (getSpecificField(nameOfItem, 2)) {
            case "CONSUMABLE":
                return ItemType.CONSUMABLE;
            case "POWERUP":
                return ItemType.POWERUP;
            default:
                break;
        }
        return null;
    }    
}
