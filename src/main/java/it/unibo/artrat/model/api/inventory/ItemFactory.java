package it.unibo.artrat.model.api.inventory;

import java.io.IOException;
/**
 * Factory to create items.
 */
public interface ItemFactory {    
    /**
     * Method to properly initialize the factory (the itemReader within it).
     * @throws IOException
     */
    void initialize() throws IOException;

    /**
     * 
     * @return a new istance of multiplierBooster Item.
     */
    Item multiplierBooster();

    /***
     * 
     * @return a new istance of lucky ticket Item.
     */
    Item luckyTicket();

     /***
     * 
     * @return a new istance of magic backpack Item.
     */
    Item magicbackpack();
    
     /***
     * 
     * @return a new istance of mysterios staff Item.
     */
    Item mysterioustaff();
    
}
