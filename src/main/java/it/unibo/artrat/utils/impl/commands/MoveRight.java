package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.api.commands.Command;

/**
 * Move right command for entity movement.
 * 
 * @author Samuele Trapani
 */
public class MoveRight implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.addDirection(Directions.RIGHT.vector());

    }

}
