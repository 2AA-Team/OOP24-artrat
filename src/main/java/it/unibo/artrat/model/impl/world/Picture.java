package it.unibo.artrat.model.impl.world;

import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.utils.impl.Point;

public class Picture extends AbstractGameObject {

    public Picture(double x, double y) {
        this.setPosition(new Point(x, y));
    }

    @Override
    public void update(long delta) {
    }

}
