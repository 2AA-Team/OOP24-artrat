package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Coin.
 * @author Cristian Di Donato.
 */
public class CoinImpl implements Coin {

    private double amount;
    private Multiplier multiplier;

    /**
     * A constructor that initializes the current funds to zero and instantiates a new money multiplier.
     */
    public CoinImpl() {
        this.amount = 0.0;
        this.multiplier = new MultiplierImpl();
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
    public void changePlayerMultipler(final double multipler) {
        if (multipler >= 0.0) {
            this.multiplier.changeCurrentMultiplier(multipler);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
