package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Standard enemy creator.
 */
public final class CreatorBaseEnemy implements EnemyFactory {

    @Override
    public Enemy createEnemy(final Point p, final double radius, final Vector2d v) {
        return new BaseEnemy(p, radius, v);
    }

}
