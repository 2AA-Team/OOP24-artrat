package it.unibo.artrat.model.api.characters;

/**
 * Factory to create enemies.
 */
public interface EnemyFactory {

    /**
     * FactoryMethod for enemies creation.
     * 
     * @return new enemy
     */
    Enemy createEnemy();

}
