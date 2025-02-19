package it.unibo.artrat.model.impl.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinImpl.class);

    /**
     * A constructor that initializes the current funds to zero and instantiates a new money multiplier.
     */
    public CoinImpl() {
        this.amount = RESET_AMOUNT;
        this.multiplier = new MultiplierImpl();
    }

     /**
     * A constructor that initializes the current funds and money multiplier from a passed Coin instance.
     * @param coin the Coin to copy.
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
        LOGGER.info("Request for the current money");
        return this.amount;
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void addCoins(final double coins) {
        LOGGER.info("Request for addition " + coins + " money");
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
        LOGGER.info("Request for spend " + coins + " money");
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
        LOGGER.info("Request for the current multiplier");
        return this.multiplier;
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public void changePlayerMultipler(final Multiplier multipler) {
        LOGGER.info("Request to change the multiplier");
        if (multipler.getCurrentMultiplier() >= 0.0) {
            this.multiplier = new MultiplierImpl(multipler);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
