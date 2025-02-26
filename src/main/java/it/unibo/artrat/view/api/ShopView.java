package it.unibo.artrat.view.api;

/**
 * ShopView interface, connected with ShopSubcontroller and used in ShopSubPanel.
 */
public interface ShopView {

    /**
     * It will be called when a message or event will pass to the controller.
     * 
     * @param message a String which represent a message.
     * @param name the name of message.
     */
    void showMessage(String message, String name);
}
