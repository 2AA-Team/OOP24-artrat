package it.unibo.artrat.model.api.market;

public interface Shop {
    /**
     * @param <T>
     * @param item item to buy
     * @return purchased item
     */
    public <T> T buy(T item);
}
