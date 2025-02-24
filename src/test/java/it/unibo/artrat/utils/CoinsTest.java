package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.impl.characters.CoinImpl;

/**
 * Test for the Coins.
 */
public class CoinsTest {
    @Test
    void testAddCoins() {
        final Coin coinsTest = new CoinImpl();
        assertThrows(IllegalArgumentException.class, () -> coinsTest.addCoins(-1.0));
        double lastCoins = coinsTest.getCurrentAmount();
        coinsTest.addCoins(0.0);
        assertEquals(lastCoins, coinsTest.getCurrentAmount());
        coinsTest.addCoins(1.0);
        assertTrue(coinsTest.getCurrentAmount() > lastCoins);
    }

    @Test
    void testSpendCoins() {
        final Coin coinsTest = new CoinImpl();
        assertThrows(IllegalArgumentException.class, () -> coinsTest.spendCoins(0.0));
        assertThrows(IllegalArgumentException.class, () -> coinsTest.spendCoins(-1.0));
        assertThrows(IllegalArgumentException.class, () -> coinsTest.spendCoins(1.0));
        final double coinToAdd = 10.0;
        final double cointToSpend = 9.2;
        coinsTest.addCoins(coinToAdd);
        coinsTest.spendCoins(cointToSpend);
        final double coinsExpeted = 0.8;
        assertEquals(coinsExpeted, coinsTest.getCurrentAmount());
    }
}
