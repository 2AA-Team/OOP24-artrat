package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

public class MoveStop implements Command {

    @Override
    public void execute(Player p) {
        final var speed = p.getSpeed();
        p.setSpeed(new Vector2d());

    }

}
