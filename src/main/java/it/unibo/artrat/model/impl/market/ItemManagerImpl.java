package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.ItemManager;

/**
 *  The model implementation of ItemManager.
 * @author Manuel Benagli.
 */
public class ItemManagerImpl implements ItemManager {
    private List<Item> itemList;
    private ItemType currenType;
    private String currentSearch = "";
    private final SortItem sortStrategy;
    private final FilterItem filterItemStrategy;
    private final SearchItem searchItemStrategy;

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
                            final SortItem sortStrategy,
                            final FilterItem filterItemStrategy,
                            final SearchItem searchItemStrategy) {
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
        return this.sortStrategy.genericSort(itemList, dir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> filterItems(final ItemType itemType) {
        this.currenType = itemType;
        final List<Item> filteredList = filterItemStrategy.genericFilter(itemList, currenType);
        return this.searchItemStrategy.genericSearch(filteredList, currentSearch);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> searchItem(final String nameToSearch) {
        currentSearch = nameToSearch;
        final List<Item> searchedList = searchItemStrategy.genericSearch(itemList, currentSearch);
        return this.filterItemStrategy.genericFilter(searchedList, this.currenType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItemList(final List<Item> passedList) {
        this.itemList = new ArrayList<>(passedList);
    }
}
