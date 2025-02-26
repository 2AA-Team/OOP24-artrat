package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.impl.characters.characters.Enemy;
import it.unibo.artrat.model.impl.characters.characters.EnemyFactory;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Enemy Factory.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createBaseEnemy(final Point topLeft, final Point bottomRight, final Vector2d v) {
        return new BaseEnemy(topLeft, bottomRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createAdvancedEnemy(final Point topLeft, final Point bottomRight, final Vector2d v) {
        return new AdvancedEnemy(topLeft, bottomRight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createAdvancedEnemy(final Point center, final double width, final double height) {
        return new AdvancedEnemy(center, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy createBaseEnemy(final Point center, final double width, final double height) {
        return new BaseEnemy(center, width, height);
    }

}
