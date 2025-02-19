package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

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
        p.addDirection(new Vector2d(1, 0));

    }

}
