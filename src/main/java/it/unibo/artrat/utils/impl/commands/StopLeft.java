package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.api.commands.Command;

/**
 * Stop move left command for entity movement.
 */
public class StopLeft implements Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        p.removeDirection(Directions.LEFT.vector());
    }

}
