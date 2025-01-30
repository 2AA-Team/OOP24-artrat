package it.unibo.artrat.model.api.market;

import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;

public interface ItemManager {

    
    List<Item> sortItemLevel();       //STESSO DISCORSO PER IL MISSION MANAGER

    List<Item> sortItemCost();

    List<Item> filterItemLevel();     //forse

    List<Item> filterItem();
}
