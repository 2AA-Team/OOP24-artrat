package it.unibo.artrat.model.api.world.roomgeneration;

public interface ObjectInsertionStrategy {

    char[][] insertSingleObject(char[][] room, char obj);
}