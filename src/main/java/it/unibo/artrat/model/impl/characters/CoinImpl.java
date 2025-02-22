package it.unibo.artrat.model.impl.characters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.characters.Coin;

/**
 * An implementation of Coin.
 * 
 * @author Cristian Di Donato.
 */
public class CoinImpl implements Coin {

    private static final double RESET_AMOUNT = 0.0;
    private double amount;

    /**
     * A constructor that initializes the current funds to zero and instantiates a
     * new money multiplier.
     */
    public CoinImpl() {
        this.amount = RESET_AMOUNT;
    }

    /**
     * A constructor that initializes the current funds and money multiplier from a
     * passed Coin instance.
     * 
     * @param coin the Coin to copy.
     */
    public CoinImpl(final Coin coin) {
        this.amount = coin.getCurrentAmount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentAmount() {
        return this.amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCoins(final double coins) {
        if (coins >= 0.0) {
            amount += coins;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spendCoins(final double coins) {
        if (coins >= 0.0 && coins <= amount) {
            amount = BigDecimal.valueOf((amount - coins))
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
