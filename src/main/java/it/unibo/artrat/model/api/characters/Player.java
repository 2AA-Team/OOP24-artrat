 package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.model.api.inventory.Inventory;

/**
 * Class that rappresents player.
 */
public interface Player extends Entity {
    
    Inventory getInventory();

    void setInventory(final Inventory inventory);

    Coin getCoin();

    void setCoin(final Coin coins);

    void increaseCoins(final double coins);

    void increaseMultiplier(final double coins);
}
