package it.unibo.artrat.model.impl.world;

import java.io.IOException;
import java.util.Random;

import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilderImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

public class FloorImpl implements Floor {

    Room[][] floor;

    public FloorImpl(String configPath) throws IOException {
        ResourceLoader<String, Integer> rl = new ResourceLoaderImpl<>();
        rl.setConfigPath(configPath);
        generateCharFloor(rl.getConfig("MAXFLOORSIZE"), rl.getConfig("MAXROOMSIZE"));
    }

    private void generateCharFloor(Integer maxFloorSize, Integer maxRoomSize) {
        Random rd = new Random();
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int floorWidth = rd.nextInt(1, maxFloorSize);
        int roomSize = rd.nextInt(5, maxRoomSize);
        int numberOfRooms = (int) Math.ceil(maxFloorSize * maxFloorSize / 2) - 1;
        RoomBuilder roomBuilder = new RoomBuilderImpl();
        int i = (int) Math.floor(floorWidth / 2);
        int j = 0;
        floor = new Room[floorWidth][floorWidth];
        while (numberOfRooms > 0) {
            int[] dir = directions[rd.nextInt(directions.length)];
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newI < floorWidth && newJ >= 0 && newJ < floorWidth) {
                i = newI;
                j = newJ;
            }
            if (floor[i][j] == null) {
                floor[i][j] = roomBuilder
                        .setRoomSize(roomSize)
                        .setNumberOfEnemy(rd.nextInt(maxRoomSize))
                        .setNumberOfValues(rd.nextInt(maxRoomSize))
                        .build();
                numberOfRooms--;
            }

        }
    }
}
