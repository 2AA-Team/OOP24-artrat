package it.unibo.artrat.view.api;

/**
 * An interface for InventorySubPanel.
 * @author Cristian Di Donato.
 */
public interface InventoryView {
    /**
     * A method that, when called, initiates and displays the inventory view.
     */
    void start();

    /**
     * A method that is invoked to display a specific message passed from the controller in response to certain actions.
     * @param messagge to be displayed.
     * @param title of the messagge window.
     */
    void displayMessage(String messagge, String title); 

    /**
     * A method that, when invoked, causes the view to be closed.
     */
    void closeWindow();
}
