package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

public class PlayerImpl extends AbstractEntity implements Player {

    public PlayerImpl(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new Vector2d());
        // TODO Auto-generated constructor stub
    }

    public PlayerImpl(Point topLeft, Point bottomRight, Vector2d v) {
        super(topLeft, bottomRight, v);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void redraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

}
