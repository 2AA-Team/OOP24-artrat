package it.unibo.artrat.model.api.characters;

/**
 * Interface that rappresent piggy bank coin.
 * @author Cristian Di Donato.
 */
public interface Coin {
    /**
     * Method to obtain the current amount of coins that player have.
     * @return the current amount of coins that player have.
     */
    double getCurrentAmount();

    /**
     * Adds the amount of money passed to the player's current money.
     * @param coins the new coins to add.
     */
    void addCoins(double coins);

    /**
     * Spends, if he has it, the amount of money equivalent to the past one.
     * @param coins the coins to remove (spend).
     */
    void spendCoins(double coins);

    /**
     * Returns the maximum acceptable coin value.
     * @return the maximum acceptable coin value.
     */
    double getMaxCoin();
}
