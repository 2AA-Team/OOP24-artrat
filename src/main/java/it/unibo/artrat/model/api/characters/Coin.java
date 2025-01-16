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
    public double getCurrentAmount();

    /**
     * Add coins to player
     * @param coins the new coins to add.
     */
    public void addCoins(double coins);

    /**
     * Spend coins of player 
     * @param coins the coins to remove (spend).
     */
    public void spendCoins(double coins);

    /**
     * 
     * @return the current multiplier that player have.
     */
    public Multiplier getCurrentMultiplier();

    /**
     * 
     * Change the current multipler of player
     */
    public void changePlayerMultipler(double multipler);

}
