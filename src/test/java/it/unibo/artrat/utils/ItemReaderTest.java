package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
 * Tester for the class ItemReader.
 * (Yaml reading about item)
 */
class ItemReaderTest {

    private final String configPath = "src" + File.separator
            + "test" + File.separator
            + "java" + File.separator
            + "it" + File.separator
            + "unibo" + File.separator
            + "artrat" + File.separator
            + "utils" + File.separator
            + "testItemReader.yaml";
    
    final ItemReader itemReader = new ItemReaderImpl();


       /**
     * test loading config path.
     */
    @Test
    void testLoading() {
        try {
            itemReader.readFromItemFile(configPath);
        } catch (IOException e) {
            fail();
        }
    }        

    /**
     * Test reading the description of item.
     */
    @Test
    void testReadingDesc() {
        try {
            itemReader.readFromItemFile(configPath);
            assertEquals("Aumento", itemReader.getDescription("Aumenta"));
            assertEquals("Lotteria", itemReader.getDescription("Biglietto"));
            assertThrows(IllegalStateException.class, () -> itemReader.getDescription("Siummete"));
        } catch (IOException e) {
            fail();
        }
    }

    /**
     * Test reading the price of item.
     */
    @Test
    void testReadingPrice() {
        try {
            itemReader.readFromItemFile(configPath);
            assertEquals(10.00, itemReader.getPrice("Aumenta"));
            assertEquals(100.00, itemReader.getPrice("Biglietto"));
            assertThrows(IllegalStateException.class, () -> itemReader.getDescription("Siummete"));
        } catch (IOException e) {
            fail();
        }
    }


    /**
     * Test reading the type of item.
     */
    @Test
    void testReadingItemType() {
        try {
            itemReader.readFromItemFile(configPath);
            assertEquals(ItemType.POWERUP, itemReader.getItemType("Aumenta"));
            assertEquals(ItemType.CONSUMABLE, itemReader.getItemType("Biglietto"));
            assertThrows(IllegalStateException.class, () -> itemReader.getDescription("Siummete"));
        } catch (IOException e) {
            fail();
        }
    }
}

