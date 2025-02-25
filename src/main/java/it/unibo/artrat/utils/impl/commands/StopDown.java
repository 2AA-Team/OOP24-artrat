package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.api.commands.CommandStop;

/**
 * Stop move down command for entity movement.
 */
public class StopDown implements CommandStop {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.removeDirection(Directions.DOWN.vector());
    }

}
