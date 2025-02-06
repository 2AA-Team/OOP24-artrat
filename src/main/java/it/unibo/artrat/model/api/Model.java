package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;

public interface Model {
    
    Inventory getInventory();

    void setInventory(Inventory inv);

    Player getPlayer();

    void setPlayer(Player player);

}
