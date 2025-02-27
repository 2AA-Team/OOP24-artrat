package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Coin;

/**
 * An abstract coin for the coin interface that handles the logic for getting and setting the current amount
 * and leaves the logic for spending and adding it to its extensions.
 * 
 * @author Cristian Di Donato.
 */
public abstract class AbstractCoin implements Coin {
    private static final double RESET_AMOUNT = 0.0;
    private static final double MAX_COINS = 9999.99;
    private double amount;

    /**
     * A constructor that initializes the current funds to the default_amount.
     */
    public AbstractCoin() {
        this.amount = RESET_AMOUNT;
    }

    /**
     * A constructor that initializes the current funds from a
     * passed Coin instance.
     * 
     * @param coin the Coin to copy.
     */
    public AbstractCoin(final Coin coin) {
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
     * An extensions-only method to set the current money after adding or spending money.
     * @param passedAmount the new amount.
     */
    protected void setAmount(final double passedAmount) {
        this.amount = passedAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxCoin() {
        return MAX_COINS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void addCoins(double coins);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void spendCoins(double coins);
}
