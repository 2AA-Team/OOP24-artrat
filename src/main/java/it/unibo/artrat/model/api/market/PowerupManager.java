package it.unibo.artrat.model.api.market;

import java.util.List;

public interface PowerupManager {

    
    List<Powerup> sortPowerupLevel();       //STESSO DISCORSO PER IL MISSION MANAGER

    List<Powerup> sortPowerupCost();

    List<Powerup> filterPowerupLevel();     //forse

}
