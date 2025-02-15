package it.unibo.artrat.model.impl.world;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilder;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationFile;
import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * implementation of interface floor.
 */
public class FloorImpl implements Floor {

    private static final Random RANDOM = new Random();
    private Set<AbstractGameObject> floorStructure = new HashSet<>();
    private Set<AbstractGameObject> floorEnemies = new HashSet<>();
    private Set<AbstractGameObject> floorValues = new HashSet<>();
    private boolean[][] floorMap;
    private final int maxFloorSize;
    private final int maxRoomSize;

    /**
     * constructor that set the configuration file path.
     * config file is used to get stantard values.
     * 
     * @param rl resource loader
     * @throws IOException caused by generation from file
     */
    public FloorImpl(final ResourceLoader<String, Double> rl) throws IOException {
        maxFloorSize = (int) Math.floor(rl.getConfig("MAX_FLOOR_SIZE"));
        maxRoomSize = (int) Math.floor(rl.getConfig("MAX_ROOM_SIZE"));
        if (maxFloorSize <= 1 || maxRoomSize <= 4) {
            throw new IllegalStateException("Floor or Room size has been modified.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getWalls() {
        return floorStructure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getEnemies() {
        return floorEnemies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getValues() {
        return floorValues;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateFloorSet() throws IOException {
        floorStructure = new HashSet<>();
        floorEnemies = new HashSet<>();
        floorValues = new HashSet<>();
        final int floorSize = RANDOM.nextInt(1, this.maxFloorSize);
        final int roomSize = RANDOM.nextInt(5, this.maxRoomSize);
        this.generateRoomsStructure(floorSize);
        this.generateEffectiveRooms(roomSize);
    }

    /**
     * fullfill the boolean matrix rapresenting the structure of the floor.
     * 
     * @param floorSize floor size
     */
    private void generateRoomsStructure(final int floorSize) {
        final int[][] directions = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        int numberOfRooms = (int) Math.ceil((double) floorSize * floorSize / 2);
        int i = (int) Math.floor((double) floorSize / 2);
        int j = floorSize - 1;
        this.floorMap = new boolean[floorSize][floorSize];
        while (numberOfRooms > 0) {
            if (!floorMap[i][j]) {
                floorMap[i][j] = true;
                numberOfRooms--;
            }
            final int[] dir = directions[RANDOM.nextInt(directions.length)];
            final int newI = i + dir[0];
            final int newJ = j + dir[1];
            if (newI >= 0 && newI < floorSize && newJ >= 0 && newJ < floorSize) {
                i = newI;
                j = newJ;
            }
        }
    }

    /**
     * fullfill the sets that rapresent the rooms.
     * 
     * @param roomSize size of the room
     * @throws IOException if link for the rooms json doesnt exist
     */
    private void generateEffectiveRooms(final int roomSize) throws IOException {
        final List<RoomGenerationStrategy> generations = List.of(
                new RoomGenerationEmpty(),
                new RoomGenerationFile("src/main/java/it/unibo/artrat/resources/premademaze/rooms.json"));
        RoomBuilder builder = new RoomBuilder();
        builder = builder.insertRoomSize(roomSize);
        for (int i = 0; i < floorMap.length; i++) {
            for (int j = 0; j < floorMap.length; j++) {
                if (isARoom(j, i)) {
                    if (j == (int) Math.floor((double) floorMap.length / 2) && i == floorMap.length - 1) {
                        builder = builder.insertGenerationStrategy(new RoomGenerationEmpty());
                        builder = builder.insertNumberOfEnemy(0);
                        builder = builder.insertNumberOfValues(0);
                    } else {
                        builder = builder.insertGenerationStrategy(generations.get(RANDOM.nextInt(generations.size())));
                        builder = builder.insertNumberOfEnemy(RANDOM.nextInt(roomSize));
                        builder = builder.insertNumberOfValues(RANDOM.nextInt(roomSize));
                    }
                    builder = builder.insertPassages(isARoom(j, i - 1), isARoom(j + 1, i), isARoom(j, i + 1),
                            isARoom(j - 1, i));
                    addNewRoom(builder.build(), j, i, roomSize);
                }
            }
        }
    }

    /**
     * checks if a room is located in a certain location.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return true if is a room
     */
    private boolean isARoom(final int x, final int y) {
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
        this.floorStructure.addAll(tmpStruct);

        final Set<AbstractGameObject> tmpValues = room.getValues();
        tmpValues.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.floorValues.addAll(tmpValues);

        final Set<AbstractGameObject> tmpEnemies = room.getEnemies();
        tmpEnemies.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.floorEnemies.addAll(tmpEnemies);
    }

    /**
     * public void print() {
     * int sizeTot = this.maxFloorSize * this.maxRoomSize;
     * for (double i = 0; i < sizeTot; i++) {
     * for (double j = 0; j < sizeTot; j++) {
     * final double x = j;
     * final double y = i;
     * if (this.roomStructure.stream()
     * .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
     * {
     * System.out.print("#");
     * } else if (this.roomEnemies.stream()
     * .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
     * {
     * System.out.print("X");
     * } else if (this.roomValues.stream()
     * .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
     * {
     * System.out.print("Y");
     * } else {
     * System.out.print(" ");
     * }
     * }
     * System.out.println();
     * }
     * }
     */
}
