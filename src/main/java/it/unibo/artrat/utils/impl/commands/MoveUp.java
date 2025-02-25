package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.api.commands.CommandMove;

/**
 * Move up command for entity movement.
 * 
 * @author Samuele Trapani
 */
public class MoveUp implements CommandMove {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.addDirection(Directions.UP.vector());

    }

}
