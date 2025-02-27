package it.unibo.artrat.model.api.characters;

/**
 * Class that rappresent the concept of multiplier for coins. 
 * When player receive coins at the end of the game, this component multiple the amount.
 * @author Cristian Di Donato.
 */
public interface Multiplier {
    /**
     * Method for obtaining the player's current multiplier.
     * @return the current value of multiplier.
     */
    double getCurrentMultiplier();
    /**
     * Method that allows past money to be multiplied by the player's current multiplier.
     * @param coins the coins that player get at the end of the game.
     * @return the trasformed coin, after the multiplication.
     */
    double multipleTheCoins(double coins);

    /**
     * Returns the maximum acceptable multiplier value. 
     * Method useful only in tests 
     * (when the values ​​change the tests always check with the current maximum).
     * @return the maximum acceptable multiplier value.
     */
    double getMaxMultiplier();

    /**
     * Method for change the player's current multiplier to the past one.
     * @param multipler is new multiplier.
     */
    void changeCurrentMultiplier(double multipler);

}
