package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.ItemManager;

/**
 * The model implementation of ItemManager.
 * 
 * @author Manuel Benagli.
 */
public class ItemManagerImpl implements ItemManager {
    private List<Item> itemList;
    private ItemType currenType;
    private String currentSearch = "";
    private final SortItemStrategy sortStrategy;
    private final FilterItemStrategy filterItemStrategy;
    private final SearchItemStrategy searchItemStrategy;

    /**
     * Constructor for initializing the ItemManagerImpl with a list of items and strategies 
     * for sorting, filtering, and searching.
     * 
     * @param passedItemList the list of items to manage.
     * @param sortStrategy the strategy for sorting the items.
     * @param filterItemStrategy the strategy for filtering the items by type.
     * @param searchItemStrategy the strategy for searching items by name.
     */
    public ItemManagerImpl(final List<Item> passedItemList,
                            final SortItemStrategy sortStrategy,
                            final FilterItemStrategy filterItemStrategy,
                            final SearchItemStrategy searchItemStrategy) {
        this.itemList = new ArrayList<>(passedItemList);
        this.sortStrategy = sortStrategy;
        this.filterItemStrategy = filterItemStrategy;
        this.searchItemStrategy = searchItemStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> sortItemPrice(final int dir) {
        return this.sortStrategy.sortStrategy(itemList, dir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> filterItems(final ItemType itemType) {
        this.currenType = itemType;
        final List<Item> filteredList = filterItemStrategy.filterStrategy(itemList, currenType);
        return this.searchItemStrategy.searchStrategy(filteredList, currentSearch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> searchItem(final String nameToSearch) {
        currentSearch = nameToSearch;
        final List<Item> searchedList = searchItemStrategy.searchStrategy(itemList, currentSearch);
        return this.filterItemStrategy.filterStrategy(searchedList, this.currenType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItemList(final List<Item> passedList) {
        this.itemList = new ArrayList<>(passedList);
    }
}
