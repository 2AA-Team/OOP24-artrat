package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.ItemFactory;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;
import it.unibo.artrat.model.impl.inventory.ItemFactoryImpl;

class InventoryTest {
    @Test
    void testAddItem() {
        final Inventory inventoryTest = new InventoryImpl();
        final ItemFactory itemFactory = new ItemFactoryImpl();
        try {
            itemFactory.initialize();
        } catch (IOException e) {
            fail();
        }
        for (int i = 0; i < inventoryTest.getMaxSize() + 3; i++) {
            inventoryTest.addItem(itemFactory.luckyTicket());
        }
        assertEquals(inventoryTest.getMaxSize(), inventoryTest.getStoredItem().size());
    }
}
