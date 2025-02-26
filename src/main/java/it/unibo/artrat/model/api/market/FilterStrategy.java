package it.unibo.artrat.model.api.market;

import java.util.List;

/**
 * FilterStrategy interface used in ItemManager to define filtering behavior for items.
 * 
 * @param <X> the type of generic to be filtered.
 * @param <T> the type of the filtering criteria.
 * 
 * @author Manuel Benagli
 */
public interface FilterStrategy<X, T> {

    /**
     * filterStrategy method.
     * 
     * @param passedList the passed list.
     * @param currenType the type of the passedList
     * @return a list of generics filtered by currenType.
     */
    List<X> filterStrategy(List<X> passedList, T currenType);
}
