package it.unibo.artrat.model.api.characters;

import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Factory to create enemies.
 * 
 * @author Samuele Trapani
 */
public interface EnemyFactory {

    /**
     * FactoryMethod for base enemies creation.
     * 
     * @param topLeft     top left corner of the bounding box
     * @param bottomRight bottom right corner the bounding box
     * @param v           enemy vector
     * @return created enemy
     */
    Enemy createBaseEnemy(Point topLeft, Point bottomRight, Vector2d v);

    /**
     * FactoryMethod for advanced enemies creation.
     * 
     * @param topLeft     top left corner of the bounding box
     * @param bottomRight bottom right corner the bounding box
     * @param v           enemy vector
     * @return created enemy
     */
    Enemy createAdvancedEnemy(Point topLeft, Point bottomRight, Vector2d v);

}
