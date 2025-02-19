package it.unibo.artrat.model.impl.world;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.AbstractGameObject;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilder;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationFile;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Point;

/**
 * implementation of interface floor.
 */
public class FloorImpl implements Floor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FloorImpl.class);
    private static final Random RANDOM = new Random();
    private Set<AbstractGameObject> floorStructure = new HashSet<>();
    private Set<AbstractEntity> floorEnemies = new HashSet<>();
    private Set<AbstractGameObject> floorValues = new HashSet<>();
    private boolean[][] floorMap;
    private final double maxFloorSize;
    private final double maxRoomSize;
    private final double minFloorSize;
    private final double minRoomSize;
    private Point startPosition;
    private Point exitPosition;

    private final URL roomPath = Thread.currentThread().getContextClassLoader().getResource(
            "premademaze" + File.separator + "rooms.json");

    /**
     * constructor that set the configuration file path.
     * config file is used to get stantard values.
     * 
     * @param rl resource loader
     * @throws IOException caused by generation from file
     */
    public FloorImpl(final ResourceLoader<String, Double> rl) throws IOException {
        maxFloorSize = rl.getConfig("MAX_FLOOR_SIZE");
        maxRoomSize = rl.getConfig("MAX_ROOM_SIZE");
        minFloorSize = rl.getConfig("MIN_FLOOR_SIZE");
        minRoomSize = rl.getConfig("MIN_ROOM_SIZE");
        validateFloorAndRoomSizes();
    }

    /**
     * method to test if validate sizes.
     * 
     * @throws IOException if size of the room or floor are not valid
     */
    private void validateFloorAndRoomSizes() throws IOException {
        final int upperBound = 100;
        if (maxFloorSize >= upperBound) {
            throw new IOException("Please MAX_FLOOR_SIZE must be under " + upperBound);
        }
        if (maxFloorSize <= 1 || maxRoomSize <= 4 || minFloorSize >= maxFloorSize || minRoomSize >= maxRoomSize) {
            throw new IOException("Floor or Room size has been modified.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getWalls() {
        return new HashSet<>(floorStructure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractEntity> getEnemies() {
        return new HashSet<>(floorEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<AbstractGameObject> getValues() {
        return new HashSet<>(floorValues);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateFloorSet() throws IOException {
        floorStructure = new HashSet<>();
        floorEnemies = new HashSet<>();
        floorValues = new HashSet<>();
        final int floorSize = RANDOM.nextInt((int) this.minFloorSize, (int) this.maxFloorSize);
        final int roomSize = RANDOM.nextInt((int) this.minRoomSize, (int) this.maxRoomSize);
        this.generateRoomsStructure(floorSize);
        this.generateEffectiveRooms(roomSize);
        // print(); // TO-REMOVE
    }

    /**
     * fullfill the boolean matrix rapresenting the structure of the floor.
     * using a random walk
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
        final int maxEnemyInARoom = 3;
        final int minEnemyInARoom = 1;
        final int maxPaintingsInARoom = 4;
        final int minPaintingsInARoom = 1;
        List<RoomGenerationStrategy> generations = List.of();
        try {
            generations = List.of(
                    new RoomGenerationEmpty(),
                    new RoomGenerationFile(roomPath.toURI()));
        } catch (IOException | URISyntaxException e) {
            LOGGER.warn("Room generations method failed to build.");
        }
        RoomBuilder builder = new RoomBuilder();
        builder = builder.insertRoomSize(roomSize);
        for (int i = 0; i < floorMap.length; i++) {
            for (int j = 0; j < floorMap.length; j++) {
                if (isARoom(j, i)) {
                    if (isStartRoom(j, i)) {
                        builder = builder.insertGenerationStrategy(new RoomGenerationEmpty());
                        builder = builder.insertNumberOfEnemy(0);
                        builder = builder.insertNumberOfValues(0);
                        setStartPosition(j, i, roomSize);
                        setExitPosition(j, i, roomSize);
                    } else {
                        builder = builder.insertGenerationStrategy(generations.get(RANDOM.nextInt(generations.size())));
                        builder = builder.insertNumberOfEnemy(RANDOM.nextInt(minEnemyInARoom, maxEnemyInARoom));
                        builder = builder
                                .insertNumberOfValues(RANDOM.nextInt(minPaintingsInARoom, maxPaintingsInARoom));
                    }
                    builder = builder.insertPassages(isARoom(j, i - 1), isARoom(j + 1, i), isARoom(j, i + 1),
                            isARoom(j - 1, i));
                    addNewRoom(builder.build(), j, i, roomSize);
                }
            }
        }
    }

    /**
     * set the exit position.
     * 
     * @param x        x coordinate
     * @param y        y coordinate
     * @param roomSize room size
     */
    private void setExitPosition(final int x, final int y, final int roomSize) {
        final double tmpX = x * roomSize + Math.floor((double) roomSize / 2);
        final double tmpY = y * roomSize + roomSize - 1;
        exitPosition = new Point(tmpX, tmpY);
        this.floorStructure.removeIf((o) -> o.getPosition().equals(exitPosition));
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
     * check if is a start room.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return true if is a start room
     */
    private boolean isStartRoom(final int x, final int y) {
        return x == (int) Math.floor((double) floorMap.length / 2) && y == floorMap.length - 1;
    }

    /**
     * set the start position.
     * 
     * @param x        x coordinate
     * @param y        y coordinate
     * @param roomSize room size
     */
    private void setStartPosition(final int x, final int y, final int roomSize) {
        startPosition = new Point(
                x * roomSize + Math.floor((double) roomSize / 2),
                y * roomSize + Math.floor((double) roomSize / 2));
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

        final Set<AbstractEntity> tmpEnemies = room.getEnemies();
        tmpEnemies.forEach((w) -> w.movedPosition(roomX * roomSize, roomY * roomSize));
        this.floorEnemies.addAll(tmpEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getStartPosition() {
        return Objects.requireNonNull(startPosition);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getExitPosition() {
        return Objects.requireNonNull(exitPosition);
    }

    // public void print() {
    // double sizeTot = this.maxFloorSize * this.maxRoomSize;
    // for (double i = 0; i < sizeTot; i++) {
    // for (double j = 0; j < sizeTot; j++) {
    // final double x = j;
    // final double y = i;
    // if (this.startPosition.getX() == j && this.startPosition.getY() == i) {
    // System.out.print("_");
    // } else if (this.floorStructure.stream()
    // .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
    // {
    // System.out.print("#");
    // } else if (this.floorEnemies.stream()
    // .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
    // {
    // System.out.print("X");
    // } else if (this.floorValues.stream()
    // .anyMatch((o) -> o.getPosition().getX() == x && o.getPosition().getY() == y))
    // {
    // System.out.print("Y");
    // } else {
    // System.out.print(" ");

    // }
    // }
    // System.out.println();
    // }
    // }

}
