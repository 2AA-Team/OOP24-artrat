package it.unibo.artrat.view.api;

/**
 * An interface for InventorySubPanel.
 * @author Cristian Di Donato.
 */
public interface InventoryView {

    /**
     * A method that is invoked to display a specific message passed from the controller in response to certain actions.
     * @param messagge to be displayed.
     * @param title of the messagge window.
     */
    void displayMessage(String messagge, String title); 
}
