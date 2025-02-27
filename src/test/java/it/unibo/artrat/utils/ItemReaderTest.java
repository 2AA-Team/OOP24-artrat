package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
* Tester for the class ItemReader.
* (Yaml reading about item)
*
* @author Cristian Di Donato.
*/
class ItemReaderTest {

    private static final String TEST_FILE_NAME = "ItemReaderTest.yaml";
    private static final String AUMENTA = "Aumenta";
    private static final String BIGLIETTO = "Biglietto";
    private static final String ZAINO = "Zaino";
    private static final String INEXISTES_ITEM = "Siummete";

    /**
    * Test loading item path.
    */
    @Test
    void testLoading() {
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream("file_not_exist.yaml");
        final ItemReader itemReader = new ItemReaderImpl();
        assertThrows(org.yaml.snakeyaml.error.YAMLException.class, () -> itemReader.setPath(inputStream),
        "config file cannot be an exe");
    }

    /**
    * Test reading the name of item.
    */
    @Test
    void testReadingName() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            itemReader.setPath(inputStream);
            assertEquals("Aumenta", itemReader.getName(AUMENTA));
            assertEquals("Bi_glietto", itemReader.getName(BIGLIETTO));
            assertEquals("Zaino", itemReader.getName(ZAINO));
        } catch (IOException e) {
            fail();
        }
    }

    /**
    * Test reading the description of item.
    */
    @Test
    void testReadingDesc() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            itemReader.setPath(inputStream);
            assertEquals("Aumento", itemReader.getDescription(AUMENTA));
            assertEquals("Lotteria", itemReader.getDescription(BIGLIETTO));
            assertEquals("Cartella", itemReader.getDescription(ZAINO));
        } catch (IOException e) {
            fail();
        }
    }

    /**
    * Test reading the price of item.
    */
    @Test
    void testReadingPrice() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            itemReader.setPath(inputStream);
            assertEquals(10.00, itemReader.getPrice(AUMENTA));
            assertEquals(100.00, itemReader.getPrice(BIGLIETTO));
            assertEquals(1000.00, itemReader.getPrice(ZAINO));
        } catch (IOException e) {
            fail();
        }
    }

    /**
    * Test reading the type of item.
    */
    @Test
    void testReadingItemType() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            itemReader.setPath(inputStream);
            assertEquals(ItemType.POWERUP, itemReader.getItemType(AUMENTA));
            assertEquals(ItemType.CONSUMABLE, itemReader.getItemType(BIGLIETTO));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getItemType(ZAINO));
        } catch (IOException e) {
            fail();
        }
    }

    /**
    * Test reading all item name.
    */
    @Test
    void testReadingAllItemName() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            assertEquals(Set.of(), itemReader.getAllItemsName());
            itemReader.setPath(inputStream);
            assertEquals(Set.of("Aumenta", "Biglietto", "Zaino"),
            itemReader.getAllItemsName());
        } catch (IOException e) {
            fail();
        }
    }

        /**
        * Test for check the correct error if try to read an inexistent item.
        */
        @Test
        void testInexistentItem() {
        final ItemReader itemReader = new ItemReaderImpl();
        final InputStream inputStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(TEST_FILE_NAME);
        try {
            itemReader.setPath(inputStream);
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getName(INEXISTES_ITEM));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getDescription(INEXISTES_ITEM));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getPrice(INEXISTES_ITEM));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getItemType(INEXISTES_ITEM));
        } catch (IOException e) {
            fail();
        }
    }
}
