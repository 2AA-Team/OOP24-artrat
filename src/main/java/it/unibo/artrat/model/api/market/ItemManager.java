package it.unibo.artrat.model.api.market;

import java.util.List;

public interface ItemManager {

    
    List<Item> sortItemLevel();       //STESSO DISCORSO PER IL MISSION MANAGER

    List<Item> sortItemCost();

    List<Item> filterItemLevel();     //forse

    List<Item> filterItem();
}
