package it.unibo.artrat.model.api.market;

import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;

public interface ItemManager {

    
    List<Item> sortItemPrice();     

    List<Item> reverseSortItemPrice();

    List<Item> filterPowerupItems(); 

    List<Item> filterConsumableItems();
}
