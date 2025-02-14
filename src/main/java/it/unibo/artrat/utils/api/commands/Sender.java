package it.unibo.artrat.utils.api.commands;

/**
 * Command notification interface.
 * 
 * @author Samuele Trapani
 */
public interface Sender {
    /**
     * Notify command input.
     * 
     * @param cmd command to add to commands buffer.
     */
    void notifyCommand(Command cmd);
}
