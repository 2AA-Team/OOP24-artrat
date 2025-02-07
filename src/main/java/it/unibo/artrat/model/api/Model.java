package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.characters.Player;

public interface Model {
    
    Player getPlayer();

    void setPlayer(Player player);

}
