package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;

/**
 * Creator for new hypothetical enemies.
 */
public class CreatorAdvancedEnemy implements EnemyFactory {

    /**
     * Advanced Enemy creator method.
     */
    @Override
    public Enemy createEnemy() {
        return new AdvancedEnemy();
    }

}
