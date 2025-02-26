package it.unibo.artrat.model.api.shop;

import java.util.List;

/**
 * SortStrategy interface.
 * I am using the Strategy pattern to allow for sorting by different parameters, 
 * such as price, name, or any other future criteria.
 * This design pattern ensures flexibility and extensibility for potential future sorting options.
 * 
 * @param <X> the type of generic to sort.
 * 
 * @author Manuel Benagli
 */
public interface SortStrategy<X> {

    /**
     * sortStrategy method.
     * 
     * @param passedList a list of passed items.
     * @param dir the sorting direction (creasing or decreasing).
     * @return a list of generic creased or decreased in base of direction.
     */
    List<X> sortStrategy(List<X> passedList, int dir);
}
