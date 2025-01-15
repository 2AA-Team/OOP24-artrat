package it.unibo.artrat.model.api.market;

/**
 * Class that rappresents the item shop.
 */
public interface Shop {
    /**
     * @param <T>  item type
     * @param item item to buy
     * @return purchased item
     */
    <T> T buy(T item);
}
