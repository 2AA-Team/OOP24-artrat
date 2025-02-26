package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemFactory;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.model.impl.inventory.ItemFactoryImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * Test the consume of all the item in the game.
 * 
 * @author Cristian Di Donato.
 */
class ItemsTest {
    private final ItemFactory itemFactory = new ItemFactoryImpl();

    @Test
    void testMultiplier() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        try {
            itemFactory.initialize();
            final Item mb = itemFactory.multiplierBooster();
            final double lastMultiplier = playerTest.getMultiplier().getCurrentMultiplier();
            mb.consume(playerTest);
            assertTrue(playerTest.getMultiplier().getCurrentMultiplier() > lastMultiplier);
            assertTrue(playerTest.getMultiplier().getCurrentMultiplier() > 0.0);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testLuckyTicket() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        try {
            itemFactory.initialize();
            final Item lt = itemFactory.luckyTicket();
            final double lastCoins = playerTest.getCoin().getCurrentAmount();
            lt.consume(playerTest);
            assertTrue(playerTest.getCoin().getCurrentAmount() >= lastCoins);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testWingedBoots() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        try {
            itemFactory.initialize();
            final Item wb = itemFactory.wingedboots();
            double lastVelocity = playerTest.getVelocity();
            wb.consume(playerTest);
            assertTrue(playerTest.getVelocity() > lastVelocity);
            lastVelocity = playerTest.getVelocity();
            wb.consume(playerTest);
            assertTrue(playerTest.getVelocity() > lastVelocity);
            lastVelocity = playerTest.getVelocity();
            wb.consume(playerTest);
            assertFalse(playerTest.getVelocity() > lastVelocity);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testMagicBackpack() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        try {
            itemFactory.initialize();
            final Item mb = itemFactory.magicbackpack();
            mb.consume(playerTest);
            assertEquals(0, playerTest.getInventory().getStoredItem().size());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void testMysteriousWand() {
        final Player playerTest = new Lupino(new Point(), new HashSet<>());
        try {
            itemFactory.initialize();
            final Item mw = itemFactory.mysteriouswand();
            final int lastInvetorySize = playerTest.getInventory().getStoredItem().size();
            mw.consume(playerTest);
            assertEquals(lastInvetorySize * 2, playerTest.getInventory().getStoredItem().size());
        } catch (IOException e) {
            fail();
        }
    }
}
