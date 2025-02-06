package it.unibo.artrat.model.api.world;

import java.util.Set;

import it.unibo.artrat.model.api.AbstractGameObject;

public interface Room {

    Set<AbstractGameObject> getStructure();

    Set<AbstractGameObject> getEnemies();

    Set<AbstractGameObject> getValues();
}
