package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
    
    private Map<String, List<String>> obj = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromItemFile(final String itemPath) throws IOException {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = new FileInputStream(new File(itemPath));
        this.obj = Map.copyOf(yaml.load(inputStream));
        inputStream.close();
    }

    private String getSpecificField(final String nameOfItem, final int field) {
        final Object ob = obj.get(nameOfItem);
        if (ob != null) {
            return ((List<String>)ob).get(field);
        } else {
            throw new IllegalStateException("This conf doesn't exist.");
        }
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

    public Set<String> getAllItemsName() {
        return this.obj.keySet();
    }
}
