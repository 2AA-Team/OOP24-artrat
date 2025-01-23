package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Multiplier.
 * @author Cristian Di Donato.
 */
public class MultiplierImpl implements Multiplier {

    private double multipler = 1.0;

     /**
     * {@inheritDoc}
     */
    @Override
    public double multipleTheCoins(final double coins) {
        if (coins >= 0.0) {
            return coins * multipler;
        } else {
            throw new IllegalArgumentException();
        }
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void changeCurrentMultiplier(final double multipler) {
        if (multipler < 0.0) {
            this.multipler = multipler;
        }
        else { 
            throw new IllegalArgumentException();
        }
    }
}
