package it.unibo.artrat.model.api;

public interface GameObjectFactory {

    AbstractGameObject getWall(int x, int y);

    AbstractGameObject getPlayer(int x, int y);

    AbstractGameObject getEnemy(int x, int y);
}
