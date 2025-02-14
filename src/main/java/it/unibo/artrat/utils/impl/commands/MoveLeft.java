package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Move left command for entity movement.
 * 
 * @author Samuele Trapani
 */
public class MoveLeft implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final Player p) {
        final var speed = p.getSpeed();
        p.setSpeed(new Vector2d(-1, 0).mul(speed.module()));
    }

}
