package it.unibo.artrat.model.impl.world;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilderImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * implementation of interface floor.
 */
public class FloorImpl implements Floor {

    boolean[][] floorMap;
    private Set<AbstractGameObject> roomStructure = new HashSet<>();
    private Set<AbstractGameObject> roomEnemies = new HashSet<>();
    private Set<AbstractGameObject> roomValues = new HashSet<>();

    /**
     * constructor that set the configuration file path.
     * config file is used to get stantard values.
     */
    public FloorImpl(String configPath) throws IOException {
        ResourceLoader<String, Integer> rl = new ResourceLoaderImpl<>();
        rl.setConfigPath(configPath);
        int maxFloorSize = rl.getConfig("MAXFLOORSIZE");
        int maxRoomSize = rl.getConfig("MAXROOMSIZE");
        if (maxFloorSize <= 1 || maxRoomSize <= 4) {
            throw new IllegalStateException("Floor or Room size has been modified.");
        }
        generateFloorSet(maxFloorSize, maxRoomSize);
    }

    /**
     * generate a pseudo-random floor as a room matrix.
     * 
     * @param maxFloorSize max floor size
     * @param maxRoomSize  max room size
     */
    private void generateFloorSet(Integer maxFloorSize, Integer maxRoomSize) {
        Random rd = new Random();
        int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int floorSize = rd.nextInt(1, maxFloorSize);
        int roomSize = rd.nextInt(5, maxRoomSize + 1);
        int numberOfRooms = (int) Math.ceil(maxFloorSize * maxFloorSize / 2);
        int i = (int) Math.floor(floorSize / 2);
        int j = 0;
        RoomBuilder roomBuilder = new RoomBuilderImpl();
        floorMap = new boolean[floorSize][floorSize];
        while (numberOfRooms > 0) {
            int[] dir = directions[rd.nextInt(directions.length)];
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if (newI >= 0 && newI < floorSize && newJ >= 0 && newJ < floorSize) {
                i = newI;
                j = newJ;
            }
            if (floorMap[i][j] == false) {
                Room newRoom = roomBuilder
                        .setRoomSize(roomSize)
                        .setNumberOfEnemy(rd.nextInt(maxRoomSize))
                        .setNumberOfValues(rd.nextInt(maxRoomSize))
                        .build();
                addNewRoom(newRoom, i, j, roomSize);
                numberOfRooms--;
            }
        }
    }

    /**
     * add to the floor a new room.
     * 
     * @param room     new room to add
     * @param roomX    the X of the room in the matrix floor
     * @param roomY    the Y of the room in the matrix floor
     * @param roomSize the size of the room
     */
    private void addNewRoom(Room room, int roomX, int roomY, int roomSize) {
        Set<AbstractGameObject> tmpStruct = room.getStructure();
        tmpStruct.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomStructure.addAll(tmpStruct);

        Set<AbstractGameObject> tmpValues = room.getValues();
        tmpValues.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomValues.addAll(tmpValues);

        Set<AbstractGameObject> tmpEnemies = room.getEnemies();
        tmpEnemies.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomEnemies.addAll(tmpEnemies);
    }
}
