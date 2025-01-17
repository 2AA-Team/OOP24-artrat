package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;

/**
 * Standard enemy creator.
 */
public final class CreatorBaseEnemy implements EnemyFactory {

    @Override
    public Enemy createEnemy() {
        return new BaseEnemy();
    }

}
