package it.unibo.artrat.model.api.market;

import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;

public interface ItemManager {

    
    List<Item> sortItemPrice();     

    List<Item> reverseSortItemPrice();

    List<Item> filterItems(ItemType itemType);
    
    List<Item> searchItem(Item item); 
}
