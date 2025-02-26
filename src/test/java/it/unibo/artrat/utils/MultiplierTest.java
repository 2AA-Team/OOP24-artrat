package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.characters.BaseMultiplier;
import it.unibo.artrat.model.impl.characters.characters.Multiplier;

/**
 * Test the function of the Multiplier.
 * 
 * @author Cristian Di Donato.
 */
class MultiplierTest {
    @Test
    void testChangeCurrentMultiplier() {
        final Multiplier multiplierTest = new BaseMultiplier();
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.changeCurrentMultiplier(0.0));
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.changeCurrentMultiplier(-1.0));
    }

    @Test
    void testMultipleCoin() {
        final Multiplier multiplierTest = new BaseMultiplier();
        assertThrows(IllegalArgumentException.class, () -> multiplierTest.multipleTheCoins(-1.0));
        multiplierTest.changeCurrentMultiplier(2.0);
        final double coinTest = 2.0;
        assertEquals(coinTest * 2, multiplierTest.multipleTheCoins(coinTest));
        multiplierTest.changeCurrentMultiplier(4.0);
        assertNotEquals(coinTest * 2, multiplierTest.multipleTheCoins(coinTest));
    }
}
