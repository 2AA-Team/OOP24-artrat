package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Enemy;
import it.unibo.artrat.model.api.characters.EnemyFactory;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

public class EnemyFactoryImpl implements EnemyFactory {

    @Override
    public Enemy createBaseEnemy(Point topLeft, Point bottomRight, Vector2d v) {
        return new BaseEnemy(topLeft, bottomRight);
    }

    @Override
    public Enemy createAdvancedEnemy(Point topLeft, Point bottomRight, Vector2d v) {
        return new AdvancedEnemy(topLeft, bottomRight);
    }

    @Override
    public Enemy createAdvancedEnemy(Point center, double width, double height) {
        return new AdvancedEnemy(center, width, height);
    }

    @Override
    public Enemy createBaseEnemy(Point center, double width, double height) {
        return new BaseEnemy(center, width, height);
    }

}
