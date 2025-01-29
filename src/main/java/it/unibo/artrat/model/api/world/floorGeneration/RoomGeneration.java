package it.unibo.artrat.model.api.world.floorGeneration;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.utils.impl.Point;

public interface RoomGeneration {
    Set<GameObject> generateRoom(int size, Point start);
}
