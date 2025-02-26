package it.unibo.artrat.model.impl.characters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * A base multiplier implementation.
 * 
 * @author Cristian Di Donato.
 */
public class BaseMultiplier extends AbstractMultiplier {
    /**
     * A constructor that initialize a new istance of multiplier with the default
     * value.
     */
    public BaseMultiplier() {
        super();
    }

    /**
     * A constructor that initialize a new istance from a exist Multiplier.
     * 
     * @param mpd the passed Multiplier.
     */
    public BaseMultiplier(final Multiplier mpd) {
        super(mpd);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double multipleTheCoins(final double coins) {
        if (coins >= 0.0) {
            return BigDecimal.valueOf(coins * getCurrentMultiplier())
                            .setScale(2, RoundingMode.UP)
                            .doubleValue();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
