package it.unibo.artrat.model.api.market;

import java.util.List;

/**
 * GenericSort interface.
 * 
 * @param <X> the type of generic to sort.
 * 
 * @author Manuel Benagli
 */
public interface GenericSort<X> {

    /**
     * genericSort method.
     * 
     * @param passedList a list of passed items.
     * @param dir the sorting direction (creasing or decreasing).
     * @return a list of generic creased or decreased in base of direction.
     */
    List<X> genericSort(List<X> passedList, int dir);
}
