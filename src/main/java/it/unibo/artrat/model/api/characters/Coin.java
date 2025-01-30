package it.unibo.artrat.model.api.characters;

/**
 * Interface that rappresent piggy bank coin.
 * @author Cristian Di Donato.
 */
public interface Coin {
    /**
     * 
     * @return the current amount of coins that player have.
     */
    double getCurrentAmount();

    /**
     * Add coins to player.
     * @param coins the new coins to add.
     */
    void addCoins(double coins);

    /**
     * Spend coins of player.
     * @param coins the coins to remove (spend).
     */
    void spendCoins(double coins);

    /**
     * 
     * @return the current multiplier that player have.
     */
    Multiplier getCurrentMultiplier();

    /**
     * 
     * Change the current multipler of player.
     * @param multipler the new multiplier.
     */
    void changePlayerMultipler(double multipler);

}
