package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.Directions;

/**
 * Move left command for entity movement.
 * 
 * @author Samuele Trapani
 */
public class MoveLeft implements CommandMove {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.addDirection(Directions.LEFT.vector());

    }

}
