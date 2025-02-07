package it.unibo.artrat.model.impl.world;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import it.unibo.artrat.model.api.AbstractGameObject;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilder;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationFile;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * implementation of interface floor.
 */
public class FloorImpl implements Floor {

    private static final Random RANDOM = new Random();
    private final Set<AbstractGameObject> roomStructure = new HashSet<>();
    private final Set<AbstractGameObject> roomEnemies = new HashSet<>();
    private final Set<AbstractGameObject> roomValues = new HashSet<>();
    private boolean[][] floorMap;

    /**
     * constructor that set the configuration file path.
     * config file is used to get stantard values.
     * 
     * @param configPath configuration file path
     */
    public FloorImpl(final String configPath) throws IOException {
        final ResourceLoader<String, Integer> rl = new ResourceLoaderImpl<>();
        rl.setConfigPath(configPath);
        final int maxFloorSize = rl.getConfig("MAXFLOORSIZE");
        final int maxRoomSize = rl.getConfig("MAXROOMSIZE");
        if (maxFloorSize <= 1 || maxRoomSize <= 4) {
            throw new IllegalStateException("Floor or Room size has been modified.");
        }
        generateFloorSet(maxFloorSize, maxRoomSize);
        this.print(maxRoomSize * maxFloorSize);
    }

    /**
     * generate a pseudo-random floor as a room matrix.
     * 
     * @param maxFloorSize max floor size
     * @param maxRoomSize  max room size
     */
    private void generateFloorSet(final int maxFloorSize, final int maxRoomSize) {
        final int floorSize = RANDOM.nextInt(1, maxFloorSize);
        final int roomSize = RANDOM.nextInt(8, maxRoomSize + 1);
        this.generateRoomsStructure(floorSize);
        try {
            this.generateEffectiveRooms(roomSize);
        } catch (IOException e) {
            throw new IllegalStateException("Configuration room file doesnt found.");
        }
    }

    private void generateRoomsStructure(int floorSize) {
        final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int numberOfRooms = (int) Math.ceil((double) floorSize * floorSize / 2);
        int i = (int) Math.floor((double) floorSize / 2);
        int j = 0;
        this.floorMap = new boolean[floorSize][floorSize];
        while (numberOfRooms > 0) {
            final int[] dir = directions[RANDOM.nextInt(directions.length)];
            final int newI = i + dir[0];
            final int newJ = j + dir[1];
            if (newI >= 0 && newI < floorSize && newJ >= 0 && newJ < floorSize) {
                i = newI;
                j = newJ;
            }
            if (!floorMap[i][j]) {
                floorMap[i][j] = true;
                numberOfRooms--;
            }
        }
    }

    private void generateEffectiveRooms(int roomSize) throws IOException {
        List<RoomGenerationStrategy> generations = List.of(
                new RoomGenerationEmpty(),
                new RoomGenerationFile("src/main/java/it/unibo/artrat/resources/premademaze/rooms.json"));
        RoomBuilder builder = new RoomBuilder();
        builder = builder.insertRoomSize(roomSize);
        for (int i = 0; i < floorMap.length; i++) {
            for (int j = 0; j < floorMap.length; j++) {
                if (isARoom(j, i)) {
                    builder = builder.insertGenerationStrategy(generations.get(RANDOM.nextInt(generations.size())));
                    builder = builder.insertNumberOfEnemy(RANDOM.nextInt(roomSize));
                    builder = builder.insertNumberOfValues(RANDOM.nextInt(roomSize));
                    builder = builder.insertPassages(isARoom(j, i - 1), isARoom(j + 1, i), isARoom(j, i + 1),
                            isARoom(j - 1, i));
                    addNewRoom(builder.build(), j, i, roomSize);
                }
            }
        }
    }

    private boolean isARoom(int x, int y) {
        if (x < 0 || y < 0 || x >= this.floorMap.length || y >= this.floorMap.length) {
            return false;
        } else {
            return floorMap[x][y];
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
    private void addNewRoom(final Room room, final int roomX, final int roomY, final int roomSize) {
        final Set<AbstractGameObject> tmpStruct = room.getStructure();
        tmpStruct.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomStructure.addAll(tmpStruct);

        final Set<AbstractGameObject> tmpValues = room.getValues();
        tmpValues.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomValues.addAll(tmpValues);

        final Set<AbstractGameObject> tmpEnemies = room.getEnemies();
        tmpEnemies.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.roomEnemies.addAll(tmpEnemies);
    }

    private void print(double sizeTot) {
        for (double i = 0; i < sizeTot; i++) {
            for (double j = 0; j < sizeTot; j++) {
                final double x = j;
                final double y = i;
                if (this.roomStructure.stream()
                        .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y)) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
