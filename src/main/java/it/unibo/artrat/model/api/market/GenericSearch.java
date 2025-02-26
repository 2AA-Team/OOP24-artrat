package it.unibo.artrat.model.api.market;

import java.util.List;

/**
 * Interface for defining search strategies to filter items based on a search term.
 * 
 * @param <X> the type of generic to search (e.g Item).
 * @param <T> the type of the search term (e.g., String).
 * 
 * @author Manuel Benagli
 */
public interface GenericSearch<X, T> {

    /**
     * searchStrategy method, it will be used in itemManager with items.
     * 
     * @param passedList a list of generic. 
     * @param currentSearch a generic currentSearch.
     * @return a list of generic.
     */
    List<X> searchStrategy(List<X> passedList, T currentSearch);
}
