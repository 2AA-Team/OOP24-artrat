package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;

/**
 * An implementation of ItemReader.
 */
public class ItemReaderImpl implements ItemReader {
    /**
     * The actual reader from yaml.
     */
    private final ResourceLoader<String, List<String>> valueOfYaml;

    /**
     * A constructor that initializes an instance of Resource Loader.
     */
    public ItemReaderImpl() {
        this.valueOfYaml = new ResourceLoaderImpl<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromItemFile(final URI itemPath) throws IOException {
        this.valueOfYaml.setConfigPath(itemPath);
    }

    /**
     * 
     * @param nameOfItem n
     * @param field f
     * @return s
     */
    private String getSpecificField(final String nameOfItem, final int field) {
        return valueOfYaml.getConfig(nameOfItem).get(field);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(final String nameOfItem) {
        return getSpecificField(nameOfItem, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final String nameOfItem) {
        return Double.parseDouble(getSpecificField(nameOfItem, 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(final String nameOfItem) {
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

    /**
     * s.
     */
    @Override
    public Set<String> getAllItemsName() {
        return this.obj.keySet();
    }
}
