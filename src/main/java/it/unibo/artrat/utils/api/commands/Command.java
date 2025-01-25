package it.unibo.artrat.utils.api.commands;

/**
 * Command interface for command pattern.
 */
public interface Command {
    /**
     * Command execution.
     */
    void execute(); // TODO: parameter must be Room/Floor for game object uses
}
