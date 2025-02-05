package it.unibo.artrat.model.impl.world.roomgeneration;

import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.RoomSymbols;

public class RoomGenerationEmpty implements RoomGenerationStrategy {

    @Override
    public char[][] generateCharRoom(int size) {
        char[][] room = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == size - 1 || j == size - 1 || i == 0 || j == 0) {
                    room[i][j] = RoomSymbols.WALL.getSymbol();
                } else {
                    room[i][j] = RoomSymbols.EMPTY_SPACE.getSymbol();
                }
            }
        }
        return room;
    }

}
