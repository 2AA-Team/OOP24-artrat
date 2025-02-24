package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.ItemManager;

/**
 *  The model implementation of ItemManager.
 */
public class ItemManagerImpl implements ItemManager {
    private List<Item> itemList;
    private ItemType currenType;
    private String currentSearch = "";

    /**
     * Item Manager constructor.
     * @param passedItemList a list of items read.
     */
    public ItemManagerImpl(final List<Item> passedItemList) {
        this.itemList = new ArrayList<>(passedItemList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> sortItemPrice(final int dir) {
        Comparator<Item> sortingDir = Comparator.comparing(Item::getPrice);
        if (dir == 0) {
            sortingDir = sortingDir.reversed();
        }
        return itemList.stream()
            .sorted(sortingDir)
            .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> filterItems(final ItemType itemType) {
        this.currenType = itemType;
        return filter(search(itemList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> searchItem(final String nameToSearch) {
        currentSearch = nameToSearch;
        return search(filter(itemList));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateItemList(final List<Item> passedList) {
        this.itemList = new ArrayList<>(passedList);
    }

    /*
     * 
     */
    private List<Item> filter(final List<Item> passedList) {
        if (currenType == null) {
            return new ArrayList<>(passedList.stream().collect(Collectors.toList()));
        }
        return new ArrayList<>(passedList.stream()
            .filter(it -> it.getType().equals(currenType))
            .collect(Collectors.toList()));
    }

    /*
     * 
     */
    private List<Item> search(final List<Item> passedList) {
        return new ArrayList<>(passedList.stream()
            .filter(it -> it.getClass().getSimpleName().toLowerCase(Locale.getDefault())
            .startsWith(currentSearch.trim().toLowerCase(Locale.getDefault())))
            .collect(Collectors.toList()));
    }
}
