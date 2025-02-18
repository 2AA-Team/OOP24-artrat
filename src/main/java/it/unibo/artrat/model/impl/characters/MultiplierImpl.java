package it.unibo.artrat.model.impl.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Multiplier.
 * @author Cristian Di Donato.
 */
public class MultiplierImpl implements Multiplier {

    private static final double DEFAULT_MULTIPLIER = 1.0;
    private double multipler;
    private static final Logger LOGGER = LoggerFactory.getLogger(SingleThreadedGame.class);

    /**
     * A constructor that initialize a new istance of multiplier with the default value.
     */
    public MultiplierImpl() {
        this.multipler  = DEFAULT_MULTIPLIER;
    }

    /**
     * A constructor that initialize a new istance from a exist Multiplier.
     * @param mpd the passed Multiplier.
     */
    public MultiplierImpl(final Multiplier mpd) {
        this.multipler = mpd.getCurrentMultiplier();
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public double multipleTheCoins(final double coins) {
        LOGGER.info("Request to multiply the past coins with the current multiplier");
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
        LOGGER.info("Request to change the current multiplier with the past one.");
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
        LOGGER.info("Request to obtain the current multiplier.");
        return this.multipler;
    }
}
