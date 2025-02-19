package it.unibo.artrat.utils.api;

import java.io.IOException;
import java.net.URI;
import java.util.Set;

import it.unibo.artrat.model.api.inventory.ItemType;

/**
 * An interface that represents a reader for all types of Items present in
 * ArtRat, from a Yaml file.
 */
public interface ItemReader {

    /**
     * Method that load all itemPath data.
     * 
     * @param itemPath file that contains all item.
     * @throws IOException if configPath not represent anything:
     */
    void setItemPath(URI itemPath) throws IOException;

    /**
     * 
     * @param nameOfItem desired item from the file.
     * @return The description of the desired item.
     */
    String getDescription(String nameOfItem);

    /**
     * 
     * @param nameOfItem desired item from the file.
     * @return The price of the desired item.
     */
    double getPrice(String nameOfItem);

    /**
     * 
     * @param nameOfItem desired item from the file.
     * @return The ItemTypes of the desired item.
     */
    ItemType getItemType(String nameOfItem);

    /**
     * 
     * @return all items name.
     */
    Set<String> getAllItemsName();
}
