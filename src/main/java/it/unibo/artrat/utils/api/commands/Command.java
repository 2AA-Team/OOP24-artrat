package it.unibo.artrat.utils.api.commands;

import it.unibo.artrat.model.api.characters.Player;

/**
 * Command interface for command pattern.
 * 
 * @author Samuele Trapani
 */
public interface Command {
    /**
     * Command execution.
     * 
     * @param p player
     */
    void execute(Player p);
}
