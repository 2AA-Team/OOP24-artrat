package it.unibo.artrat.model.impl.market;

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
    private final List<Item> itemList;

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
        if (itemType == null) {
            return itemList.stream().collect(Collectors.toList());
        }
        return itemList.stream()
            .filter(it -> it.getType().equals(itemType))
            .collect(Collectors.toList());
    }

    /**
     * s.
     */
    @Override
    public List<Item> searchItem(final String nameToSearch) {
        return itemList.stream()
            .filter(it -> it.getClass().getSimpleName().toLowerCase().startsWith(nameToSearch.trim().toLowerCase(Locale.ROOT)))
            .collect(Collectors.toList());
    }
}
