package it.unibo.artrat.model.api.characters;

/**
 * Class that rappresent the concept of multiplier for coins. 
 * When player receive coins at the end of the game, this component multiple the amount.
 * @author Cristian Di Donato.
 */
public interface Multiplier {

    /**
     * 
     * @param coins the coins that player get at the end of the game.
     * @return the trasformed coin, after the multiplication.
     */
    public double multipleTheCoins(double coins);

    /**
     * 
     * Change the current multiplier
     */
    public void changeCurrentMultiplier(double multipler);

}
