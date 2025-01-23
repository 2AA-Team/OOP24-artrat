package it.unibo.artrat.view.api;

import java.util.List;

import it.unibo.artrat.model.api.market.Item;

/**
 * An interface for InvetoryObserver for InvetorySubPanel.
 * @author Cristian Di Donato.
 */

public interface InventoryViewObserver {
    
    public List<Item> getStoredItem();

    public boolean useItem(Item passedItem);

    public String getTypeName(Item passedItem);

    public void getDescription(Item passedItem);

    public void quit();
}
