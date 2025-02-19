package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
* Tester for the class ItemReader.
* (Yaml reading about item)
*/
class ItemReaderTest {


    private static final String TEST_FILE_NAME = "ItemReaderTest.yaml";
    private final List<String> itemInTest = List.of("Aumenta", "Biglietto", "Zaino");

/**
* test loading item path.
*/
    @Test
    void testLoading() {
        final ItemReader itemReader = new ItemReaderImpl();
        assertThrows(IllegalArgumentException.class, () -> itemReader.setItemPath(new URI("file_not_exist")),
                "config file cannot be an exe");
    }

    /**
    * Test reading the description of item.
    */
    @Test
    void testReadingDesc() {
        final ItemReader itemReader = new ItemReaderImpl();
        final URI uri;
        try {
            uri = Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
                    .toURI();
            itemReader.setItemPath(uri);
            assertEquals("Aumento", itemReader.getDescription(itemInTest.get(0)));
            assertEquals("Lotteria", itemReader.getDescription(itemInTest.get(1)));
            assertEquals("Cartella", itemReader.getDescription(itemInTest.get(2)));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getDescription("Siummete"));
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    /**
    * Test reading the price of item.
    */
    @Test
    void testReadingPrice() {
        final ItemReader itemReader = new ItemReaderImpl();
        final URI uri;
        try {
            uri = Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
                    .toURI();
            itemReader.setItemPath(uri);
            assertEquals(10.00, itemReader.getPrice(itemInTest.get(0)));
            assertEquals(100.00, itemReader.getPrice(itemInTest.get(1)));
            assertEquals(1000.00, itemReader.getPrice(itemInTest.get(2)));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getPrice("Siummete"));
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    /**
    * Test reading the type of item.
    */
    @Test
    void testReadingItemType() {
        final ItemReader itemReader = new ItemReaderImpl();
        final URI uri;
        try {
            uri = Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
                    .toURI();
            itemReader.setItemPath(uri);
            assertEquals(ItemType.POWERUP, itemReader.getItemType(itemInTest.get(0)));
            assertEquals(ItemType.CONSUMABLE, itemReader.getItemType(itemInTest.get(1)));
            assertThrows(IllegalArgumentException.class, () ->
            itemReader.getItemType("Siummete"));
            assertThrows(IllegalStateException.class, () ->
            itemReader.getItemType(itemInTest.get(2)));
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }

    /**
     * Test reading all item name.
     */
    @Test
    void testReadingAllItemName() {
        final ItemReader itemReader = new ItemReaderImpl();
        final URI uri;
        try {
            assertEquals(Set.of(), itemReader.getAllItemsName());
            uri = Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
                    .toURI();
            itemReader.setItemPath(uri);
            assertEquals(Set.of("Aumenta", "Biglietto", "Zaino"), itemReader.getAllItemsName());
        } catch (URISyntaxException | IOException e) {
            fail();
        }
    }
}
