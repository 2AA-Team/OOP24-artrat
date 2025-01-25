package it.unibo.artrat.utils.api;

import java.io.IOException;

import it.unibo.artrat.model.impl.market.ItemType;

/**
 * An interface that represents a reader for all types of Items present in ArtRat, from a Yaml file.
 */
public interface ItemReader {

    /**
     * Method that load all itemPath data.
     * @param itemPath file that contains all item.
     * @throws IOException if configPath not represent anything:
     */
    void readFromItemFile(String itemPath) throws IOException;
    
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
     * @return The ItemType of the desired item.
     */
    ItemType getItemType(String nameOfItem);
    
}
