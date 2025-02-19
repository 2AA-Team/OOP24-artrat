package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Stop movement command for entity movement.
 * 
 * @author Samuele Trapani
 */
public class MoveStop implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {

        p.setSpeed(new Vector2d());

    }

}
