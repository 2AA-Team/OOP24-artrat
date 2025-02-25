package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An abstract multiplier for the multiplier interface that handles the logic for getting 
 * and changing the current multiplier and leaves that for coin multiplication to extensions.
 * 
 * @author Cristian Di Donato.
 */
public abstract class AbstractMultiplier implements Multiplier {
    private static final double DEFAULT_MULTIPLIER = 1.0;
    private double multipler;

    /**
     * A constructor that initialize a new istance of multiplier with the default
     * value.
     */
    public AbstractMultiplier() {
        this.multipler = DEFAULT_MULTIPLIER;
    }

    /**
     * A constructor that initialize a new istance from a exist Multiplier.
     * 
     * @param mpd the passed Multiplier.
     */
    public AbstractMultiplier(final Multiplier mpd) {
        this.multipler = mpd.getCurrentMultiplier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeCurrentMultiplier(final double multipler) {
        if (multipler > 0.0) {
            this.multipler = multipler;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentMultiplier() {
        return this.multipler;
    }

      /**
     * {@inheritDoc}
     */
    @Override
    public abstract double multipleTheCoins(double coins);
}
