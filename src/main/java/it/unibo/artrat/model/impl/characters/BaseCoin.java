package it.unibo.artrat.model.impl.characters;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.unibo.artrat.model.api.characters.Coin;

/**
 * A base coin implementation.
 * 
 * @author Cristian Di Donato.
 */
public class BaseCoin extends AbstractCoin {
    /**
     * A constructor that initializes the current funds to the default_amount.
     */
    public BaseCoin() {
        super();
    }

    /**
     * A constructor that initializes the current funds from a
     * passed Coin instance.
     * 
     * @param coin the Coin to copy.
     */
    public BaseCoin(final Coin coin) {
        super(coin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCoins(final double coins) {
        if (coins >= 0.0) {
            double amount = getCurrentAmount();
            amount = BigDecimal.valueOf(amount + coins)
                    .setScale(2, RoundingMode.UP)
                    .doubleValue();
            setAmount(amount);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spendCoins(final double coins) {
        if (coins > 0.0 && coins <= getCurrentAmount()) {
            double amount = getCurrentAmount();
            amount = BigDecimal.valueOf(amount - coins)
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
            setAmount(amount);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
