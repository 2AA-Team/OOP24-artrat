package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.characters.Multiplier;
import it.unibo.artrat.model.impl.characters.MultiplierImpl;

/**
 * Test the function of the Multiplier.
 */
class MultiplierTest {
    @Test
    void testChangeCurrentMultiplier() {
        final Multiplier multiplierTest = new MultiplierImpl();
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.changeCurrentMultiplier(0.0));
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.changeCurrentMultiplier(-1.0));
    }

    @Test
    void testMultipleCoin() {
        final Multiplier multiplierTest = new MultiplierImpl();
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.multipleTheCoins(-1.0));
        multiplierTest.changeCurrentMultiplier(2.0);
        final double coinTest = 2.0;
        assertEquals(coinTest * 2, multiplierTest.multipleTheCoins(coinTest));
        multiplierTest.changeCurrentMultiplier(4.0);
        assertNotEquals(coinTest * 2, multiplierTest.multipleTheCoins(coinTest));
    }
}
