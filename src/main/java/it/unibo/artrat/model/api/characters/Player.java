package it.unibo.artrat.model.api.characters;

import java.util.List;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.inventory.Inventory;

/**
 * Class that rappresents player.
 * 
 * @author Samuele Trapani
 */
public interface Player extends Entity {
    /**
     * A method that permit to get a copy of the current inventory of player.
     * 
     * @return a copy of the current inventory.
     */
    Inventory getInventory();

    /**
     * A method that change the current inventory of player with the passed one.
     * 
     * @param inventory the new inventory.
     */
    void setInventory(Inventory inventory);

    /**
     * A method that permit to get the current Coin of player.
     * 
     * @return current coin
     */
    Coin getCoin();

    /**
     * A method that change the current coin of player with the passed one.
     * 
     * @param coins the new coins
     */
    void setCoin(Coin coins);

    /**
     * A method that increase the current coins of player with the passed amount.
     * 
     * @param coins
     */
    void increaseCoins(double coins);

    /**
     * A method that decrease the current coins of player with the passed amount.
     * 
     * @param coins
     */
    void spendCoins(double coins);

    /**
     * 
     * @return the current multiplier that player have.
     */
    Multiplier getMultiplier();

    /**
     * 
     * Change the current multipler of player with the passed one.
     * @param multipler the new multiplier.
     */
    void setMultipler(Multiplier multipler);

    /**
     * 
     * @return the current Collectable list.
     */
    List<Collectable> getColletableList();

    /**
     * A method that change the current collectable list with the passed one.
     * @param passedCollecatble
     */
    void setColletableList(List<Collectable> passedCollecatble);

    /**
     * A method that add a new collectable .
     * @param passedCollectable the collectable to add
     */
    void addCollectable(Collectable passedCollectable);

    /**
     * A method that increase the current coin multiplier, by multiplie the current
     * multiplier.
     * with the passed one.
     * 
     * @param multiple
     */
    void increaseMultiplier(double multiple);

    /**
     * A method that permit to copy a passed player.
     * 
     * @return a copy of passed Player.
     */
    Player copyPlayer();

    void obtainCollectable();
}
