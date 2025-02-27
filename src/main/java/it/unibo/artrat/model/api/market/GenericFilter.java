package it.unibo.artrat.model.api.market;

import java.util.List;

/**
 * GenericFilter interface used in ItemManager to define filtering behavior for items.
 * 
 * @param <X> the type of generic to be filtered.
 * @param <T> the type of the filtering criteria.
 * 
 * @author Manuel Benagli
 */
public interface GenericFilter<X, T> {

    /**
     * genericFilter method.
     * 
     * @param passedList the passed list.
     * @param currenType the type of the passedList
     * @return a list of generics filtered by currenType.
     */
    List<X> genericFilter(List<X> passedList, T currenType);
}
