package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.GenericFilter;

/**
 * FilterItem class for items (using itemType).
 * I chose to use a inline condition ItemType for a better readability.
 * 
 * @author Manuel Benagli
 */
public class FilterItem implements GenericFilter<Item, ItemType> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> genericFilter(final List<Item> passedList, final ItemType currenType) {
        if (currenType == null) {
            return new ArrayList<>(passedList.stream().collect(Collectors.toList()));
        }
        return new ArrayList<>(passedList.stream()
            .filter(it -> it.getType().equals(currenType))
            .collect(Collectors.toList()));
    }
}
