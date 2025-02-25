package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.api.commands.CommandStop;

/**
 * Stop move right command for entity movement.
 */
public class StopRight implements CommandStop {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.removeDirection(Directions.RIGHT.vector());
    }

}
