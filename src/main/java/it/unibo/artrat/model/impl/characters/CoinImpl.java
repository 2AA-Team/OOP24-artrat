package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Coin.
 * @author Cristian Di Donato.
 */
public class CoinImpl implements Coin {

    private static final double RESET_AMOUNT = 0.0;
    private double amount;
    private Multiplier multiplier;

    /**
     * A constructor that initializes the current funds to zero and instantiates a new money multiplier.
     */
    public CoinImpl() {
        this.amount = RESET_AMOUNT;
        this.multiplier = new MultiplierImpl();
    }

     /**
     * A constructor that initializes the current funds and money multiplier from a passed Coin instance.
     * @param the Coin to copy.
     */
    public CoinImpl(final Coin coin) {
        this.amount = coin.getCurrentAmount();
        this.multiplier = coin.getCurrentMultiplier();
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
            amount -= coins;
        } else {
            throw new IllegalArgumentException();
        }
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public Multiplier getCurrentMultiplier() {
        return this.multiplier;
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void changePlayerMultipler(final Multiplier multipler) {
        if (multipler.getCurrentMultiplier() >= 0.0) {
            this.multiplier = new MultiplierImpl(multipler);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
