// package it.unibo.artrat.utils;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
// import static org.junit.jupiter.api.Assertions.fail;

// import java.io.IOException;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.util.Set;

// import org.junit.jupiter.api.Test;

// import it.unibo.artrat.model.api.inventory.ItemType;
// import it.unibo.artrat.utils.api.ItemReader;
// import it.unibo.artrat.utils.impl.ItemReaderImpl;

// /**
// * Tester for the class ItemReader.
// * (Yaml reading about item)
// *
// * @author Cristian Di Donato.
// */
// class ItemReaderTest {

// private static final String TEST_FILE_NAME = "ItemReaderTest.yaml";
// private static final String AUMENTA = "Aumenta";
// private static final String BIGLIETTO = "Biglietto";
// private static final String ZAINO = "Zaino";
// private static final String INEXISTES_ITEM = "Siummete";

// /**
// * Test loading item path.
// */
// @Test
// void testLoading() {
// final ItemReader itemReader = new ItemReaderImpl();
// assertThrows(IllegalArgumentException.class, () -> itemReader.setPath(new
// URI("file_not_exist")),
// "config file cannot be an exe");
// }

// /**
// * Test reading the name of item.
// */
// @Test
// void testReadingName() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertEquals("Aumenta", itemReader.getName(AUMENTA));
// assertEquals("Bi_glietto", itemReader.getName(BIGLIETTO));
// assertEquals("Zaino", itemReader.getName(ZAINO));
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }

// /**
// * Test reading the description of item.
// */
// @Test
// void testReadingDesc() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertEquals("Aumento", itemReader.getDescription(AUMENTA));
// assertEquals("Lotteria", itemReader.getDescription(BIGLIETTO));
// assertEquals("Cartella", itemReader.getDescription(ZAINO));
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }

// /**
// * Test reading the price of item.
// */
// @Test
// void testReadingPrice() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertEquals(10.00, itemReader.getPrice(AUMENTA));
// assertEquals(100.00, itemReader.getPrice(BIGLIETTO));
// assertEquals(1000.00, itemReader.getPrice(ZAINO));
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }

// /**
// * Test reading the type of item.
// */
// @Test
// void testReadingItemType() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertEquals(ItemType.POWERUP, itemReader.getItemType(AUMENTA));
// assertEquals(ItemType.CONSUMABLE, itemReader.getItemType(BIGLIETTO));
// assertThrows(IllegalArgumentException.class, () ->
// itemReader.getItemType(ZAINO));
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }

// /**
// * Test reading all item name.
// */
// @Test
// void testReadingAllItemName() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// assertEquals(Set.of(), itemReader.getAllItemsName());
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertEquals(Set.of("Aumenta", "Biglietto", "Zaino"),
// itemReader.getAllItemsName());
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }

// /**
// * Test for check the correct error if try to read an inexistent item.
// */
// @Test
// void testInexistentItem() {
// final ItemReader itemReader = new ItemReaderImpl();
// final URI uri;
// try {
// uri =
// Thread.currentThread().getContextClassLoader().getResource(TEST_FILE_NAME)
// .toURI();
// itemReader.setPath(uri);
// assertThrows(IllegalArgumentException.class, () ->
// itemReader.getName(INEXISTES_ITEM));
// assertThrows(IllegalArgumentException.class, () ->
// itemReader.getDescription(INEXISTES_ITEM));
// assertThrows(IllegalArgumentException.class, () ->
// itemReader.getPrice(INEXISTES_ITEM));
// assertThrows(IllegalArgumentException.class, () ->
// itemReader.getItemType(INEXISTES_ITEM));
// } catch (URISyntaxException | IOException e) {
// fail();
// }
// }
// }
