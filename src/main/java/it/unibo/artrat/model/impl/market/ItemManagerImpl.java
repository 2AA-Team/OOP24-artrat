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
    private ItemType currenType = null;
    private String currentSearch = "";

    /**
     * Item Manager constructor.
     * @param market a list of items read
     */
    public ItemManagerImpl(final List<Item> market) {
        this.itemList = market;
    }

    /**
     * s.
     * {@InheritDoc}
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
     * s.
     * {@InheritDoc}
     */
    @Override
    public List<Item> filterItems(final ItemType itemType) {
        this.currenType = itemType;
        return filter(search(itemList));
    }

    /**
     * s.
     */
    @Override
    public List<Item> searchItem(final String nameToSearch) {
        currentSearch = nameToSearch;
        return search(filter(itemList));
    }

    @Override
    public void updateItemList(List<Item> passedList) {
        this.itemList = new ArrayList<>(passedList);
    }

    private List<Item> filter(List<Item> passedList) {
        if (currenType == null) {
            return passedList.stream().collect(Collectors.toList());
        }
        return passedList.stream()
            .filter(it -> it.getType().equals(currenType))
            .collect(Collectors.toList());
    }

    private List<Item> search(List<Item> passedList) {
        return passedList.stream()
            .filter(it -> it.getClass().getSimpleName().toLowerCase().startsWith(currentSearch.trim().toLowerCase(Locale.ROOT)))
            .collect(Collectors.toList());
    }
}
