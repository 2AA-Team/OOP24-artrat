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
     * @param topLeft     top left corner of the bounding box
     * @param bottomRight bottom right corner the bounding box
     * @param v           enemy vector
     * @return created enemy
     */
    Enemy createEnemy(Point topLeft, Point bottomRight, Vector2d v);

}
