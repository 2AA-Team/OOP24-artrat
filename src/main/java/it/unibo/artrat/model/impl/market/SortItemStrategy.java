package it.unibo.artrat.model.impl.market;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.market.SortStrategy;

/**
 * SortItemStrategy class, which implements SortStrategy to sort items based on specific criteria.
 * 
 * @author Manuel Benagli.
 */
public class SortItemStrategy implements SortStrategy<Item> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> sortStrategy(final List<Item> passedList, final int dir) {
        Comparator<Item> sortingDir = Comparator.comparing(Item::getPrice);
        if (dir == 0) {
            sortingDir = sortingDir.reversed();
        }
        return passedList.stream()
            .sorted(sortingDir)
            .collect(Collectors.toList());
    }
}
