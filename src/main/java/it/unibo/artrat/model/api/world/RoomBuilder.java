package it.unibo.artrat.model.api.world;

import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;

public interface RoomBuilder {

    RoomBuilder setRoomSize(int roomSize);

    RoomBuilder setGenerationStrategy(RoomGenerationStrategy generationStrat);

    RoomBuilder setNumberOfEnemy(int numEnemies);

    RoomBuilder setNumberOfValues(int numValues);

    RoomBuilder setInsertionStrategy(ObjectInsertionStrategy insertStrat);

    Room build();
}
