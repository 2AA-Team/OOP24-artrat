package it.unibo.artrat.utils.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * An implementation of ItemReader.
 */
public class ItemReaderImpl implements ItemReader {
    /**
     * The actual reader from yaml.
     */
    private final ResourceLoader<String,List<String>> valueOfYaml;


    /**
     * A constructor that initializes an instance of Resource Loader.
     */
    public ItemReaderImpl() {
        this.valueOfYaml = new ResourceLoaderImpl<>();
    }

    /**
     * @author Manuel Benagli
     * @return
     */
    @Override
    public List<String> getAllItemsList(){
        return valueOfYaml.getConfig("");   //prende tutto lo yaml
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void readFromItemFile(final String itemPath) throws IOException {
        this.valueOfYaml.setConfigPath(itemPath);
    }

    private String getSpecificField(final String nameOfItem, final int field) {
        return (valueOfYaml.getConfig(nameOfItem)).get(field);
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(final String nameOfItem) {
        return getSpecificField(nameOfItem, 1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final String nameOfItem) {
        return Double.parseDouble(getSpecificField(nameOfItem, 2));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(final String nameOfItem) {
        switch (getSpecificField(nameOfItem, 3)) {
            case "CONSUMABLE":
                return ItemType.CONSUMABLE;
            case "POWERUP":
                return ItemType.POWERUP;
            default:
                break;
        }
        return null;
    }
    
    /*
    ogni opggettto nello yaml è una lista di stringhe
    game factory si piuò riusare, mi scorro la riga 

    devo passare una lista intera 
    publico
    */
}
