package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.model.impl.world.Painting;
import it.unibo.artrat.utils.impl.Point;

/**
 * Test the fuction of the Player.
 * 
 * @author Cristian Di Donato.
 */
class PlayerTest {
    @Test
    void testObtainCollectable() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        playerTest.obtainCollectable();
        assertEquals(0.0, playerTest.getCoin().getCurrentAmount());
        playerTest.addCollectable(new Painting(0, 0, 1));
        playerTest.obtainCollectable();
        assertEquals(1, playerTest.getCoin().getCurrentAmount());
        playerTest.addCollectable(new Painting(0, 0, 1));
        playerTest.addCollectable(new Painting(0, 0, 1));
        playerTest.obtainCollectable();
        assertEquals(3, playerTest.getCoin().getCurrentAmount());
    }

    @Test
    void testIncreaseMultiplier() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> playerTest.increaseMultiplier(0.0));
        assertThrows(IllegalArgumentException.class, () -> playerTest.increaseMultiplier(-1.0));
    }

    @Test
    void testIncreaseCoins() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> playerTest.increaseCoins(-1.0));
    }

    @Test
    void testSetVelocity() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        assertThrows(IllegalArgumentException.class, () -> playerTest.setVelocity(-1.0));
        assertThrows(IllegalArgumentException.class, () -> playerTest.setVelocity(0.0));
    }
}
