package it.unibo.artrat.model.api.world;

import it.unibo.artrat.model.api.GameObject;

public interface ValuableItem extends GameObject {
    public boolean stealed();

    public double getValue();
}
