package it.unibo.artrat.model.api.market;

import java.util.List;

public interface ItemManager {

    
    List<Item> sortPowerupLevel();       //STESSO DISCORSO PER IL MISSION MANAGER

    List<Item> sortPowerupCost();

    List<Item> filterPowerupLevel();     //forse

}
