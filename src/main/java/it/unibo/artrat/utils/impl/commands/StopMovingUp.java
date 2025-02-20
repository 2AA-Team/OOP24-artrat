package it.unibo.artrat.utils.impl.commands;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.commands.Command;
import it.unibo.artrat.utils.impl.Vector2d;

public class StopMovingUp implements Command {

    @Override
    public void execute(Player p) {
        p.removeDirection(new Vector2d(0, -1));
    }

}
