package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Creator for new hypothetical enemies.
 */
public class CreatorAdvancedEnemy implements EnemyFactory {

    /**
     * Advanced Enemy creator method.
     */
    @Override
    public Enemy createEnemy(final Point p, final double radius, final Vector2d v) {
        return new AdvancedEnemy(p, radius, v); // TODO
    }

}
