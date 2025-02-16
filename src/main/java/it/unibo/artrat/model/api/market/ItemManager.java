package it.unibo.artrat.model.api.market;

import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;

public interface ItemManager {

    List<Item> sortItemPrice(boolean dir);

    List<Item> filterItems(ItemType itemType);
    
    List<Item> searchItem(String nameToSearch); 
}
