package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Factory to create enemies.
 */
public interface EnemyFactory {

    /**
     * FactoryMethod for enemies creation.
     * 
     * @param bottomLeft bottom left corner of the bounding box
     * @param topRight   top right corner the bounding box
     * @param v          enemy vector
     * @return created enemy
     */
    Enemy createEnemy(Point bottomLeft, Point topRight, Vector2d v);

}
