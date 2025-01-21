package it.unibo.artrat.utils.api.commands;

import it.unibo.artrat.model.api.characters.Entity;

public interface Command {
    void execute(); // TODO: parameter must be Room/Floor for game object uses
}
