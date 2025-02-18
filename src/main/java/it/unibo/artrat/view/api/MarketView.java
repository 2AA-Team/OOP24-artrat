package it.unibo.artrat.view.api;

/**
 * A MarketView interface, which will be connected with controller.
 */
public interface MarketView {

    /**
     * It will be called when a message or event will pass to the controller.
     * @param message
     * @param name
     */
    void showMessage(String message, String name);
}
