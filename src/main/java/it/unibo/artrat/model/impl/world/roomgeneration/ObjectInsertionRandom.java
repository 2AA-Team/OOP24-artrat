package it.unibo.artrat.model.impl.world.roomgeneration;

import java.util.Arrays;
import java.util.Random;

import it.unibo.artrat.model.api.world.roomgeneration.ObjectInsertionStrategy;
import it.unibo.artrat.model.impl.world.RoomSymbols;

public class ObjectInsertionRandom implements ObjectInsertionStrategy {

    @Override
    public char[][] insertSingleObject(char[][] room, char obj) {
        char[][] copy = room;
        int i;
        int j;
        Random rd = new Random();
        if (Arrays.stream(room)
                .flatMapToInt(row -> new String(row).chars())
                .noneMatch(c -> c == RoomSymbols.EMPTY_SPACE.getSymbol())) {
            return copy;
        }
        do {
            i = rd.nextInt(room.length);
            j = rd.nextInt(room.length);
        } while (copy[i][j] != RoomSymbols.EMPTY_SPACE.getSymbol());
        copy[i][j] = obj;
        return copy;
    }

}
