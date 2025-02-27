package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.market.GenericSearch;

/**
 * SearchItem class, using items and a String of character to search.
 * 
 * @author Manuel Benagli
 */
public class SearchItem implements GenericSearch<Item, String> {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> genericSearch(final List<Item> passedList, final String currentSearch) {
        return new ArrayList<>(passedList.stream()
            .filter(it -> it.getClass().getSimpleName().toLowerCase(Locale.getDefault())
            .startsWith(currentSearch.trim().toLowerCase(Locale.getDefault())))
            .collect(Collectors.toList()));
    }
}
