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
     * @param p      center of the bounding box
     * @param radius raidus of the bounding box
     * @param v      enemy vector
     * @return created enemy
     */
    Enemy createEnemy(Point p, double radius, Vector2d v);

}
