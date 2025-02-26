package it.unibo.artrat.model.impl.world;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.unibo.artrat.model.api.Collectable;
import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.api.world.Room;
import it.unibo.artrat.model.api.world.RoomBuilder;
import it.unibo.artrat.model.api.world.floorstructure.FloorStructureGenerationStrategy;
import it.unibo.artrat.model.api.world.roomgeneration.RoomGenerationStrategy;
import it.unibo.artrat.model.impl.characters.characters.Enemy;
import it.unibo.artrat.model.impl.world.RoomImpl.RoomBuilderImpl;
import it.unibo.artrat.model.impl.world.floorstructure.FloorStructureGenerationRandomWalk;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationEmpty;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationFile;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationMatrix;
import it.unibo.artrat.model.impl.world.roomgeneration.RoomGenerationMaze;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Point;

/**
 * implementation of interface floor.
 * 
 * @author Matteo Tonelli
 */
public class FloorImpl implements Floor {

    private static final Logger LOGGER = LoggerFactory.getLogger(FloorImpl.class);
    private static final Random RANDOM = new Random();
    private List<RoomGenerationStrategy> generationsStrategy = new ArrayList<>();
    private Set<GameObject> floorStructure = new HashSet<>();
    private Set<Enemy> floorEnemies = new HashSet<>();
    private Set<Collectable> floorCollectables = new HashSet<>();
    private List<List<Boolean>> floorMap;
    private final double maxFloorSize;
    private final double maxRoomSize;
    private final double minFloorSize;
    private final double minRoomSize;
    private Point startPosition;
    private Set<GameObject> exit;

    private final InputStream roomPath = Thread.currentThread()
            .getContextClassLoader().getResourceAsStream("premademaze" + File.separator + "rooms.json");

    /**
     * constructor that set the configuration to base state.
     */
    public FloorImpl() {
        this.maxFloorSize = 2;
        this.minRoomSize = 1;
        this.maxRoomSize = 2;
        this.minFloorSize = 1;
        this.startPosition = new Point();
        this.exit = new HashSet<>();
    }

    /**
     * constructor that set the configuration file path.
     * config file is used to get stantard collectables.
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
        try {
            this.generationsStrategy = List.of(
                    new RoomGenerationEmpty(),
                    new RoomGenerationFile(roomPath),
                    new RoomGenerationMatrix(),
                    new RoomGenerationMaze());
        } catch (IOException e) {
            LOGGER.warn("Room generations method failed to build.");
        }
    }

    private FloorImpl(final Floor passedFloor) {
        this.floorCollectables = passedFloor.getCollectables();
        this.floorEnemies = passedFloor.getEnemies();
        this.floorStructure = passedFloor.getWalls();
        this.startPosition = passedFloor.getStartPosition();
        this.exit = passedFloor.getExit();
        this.maxFloorSize = passedFloor.getMaxFloorSize();
        this.minFloorSize = passedFloor.getMinFloorSize();
        this.maxRoomSize = passedFloor.getMaxRoomSize();
        this.minRoomSize = passedFloor.getMinRoomSize();
        this.generationsStrategy = passedFloor.getGenerationsStrategy();
    }

    /**
     * method to test if validate sizes.
     * 
     * @throws IOException if size of the room or floor are not valid
     */
    private void validateFloorAndRoomSizes() throws IOException {
        final int upperBoundFloor = 100;
        if (maxFloorSize >= upperBoundFloor) {
            throw new IOException("Please MAX_FLOOR_SIZE must be less of " + upperBoundFloor);
        }
        final int upperBoundRoom = 14;
        if (maxRoomSize >= upperBoundRoom) {
            throw new IOException("Please MAX_ROOM_SIZE must be less of " + upperBoundRoom);
        }
        final int lowerBoundFloor = 1;
        if (minFloorSize <= lowerBoundFloor) {
            throw new IOException("Please MIN_FLOOR_SIZE must be greater of " + lowerBoundFloor);
        }
        final int lowerBoundRoom = 6;
        if (minRoomSize <= lowerBoundRoom) {
            throw new IOException("Please MIN_FLOOR_SIZE must be greater of " + lowerBoundRoom);
        }
        if (minFloorSize >= maxFloorSize || minRoomSize >= maxRoomSize) {
            throw new IOException("Invalid range for apartment generation.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getWalls() {
        return new HashSet<>(floorStructure);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Enemy> getEnemies() {
        return new HashSet<>(floorEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Collectable> getCollectables() {
        return new HashSet<>(floorCollectables);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateFloorSet() throws IOException {
        this.validateFloorAndRoomSizes();
        floorStructure = new HashSet<>();
        floorEnemies = new HashSet<>();
        floorCollectables = new HashSet<>();
        final int floorSize = RANDOM.nextInt((int) this.minFloorSize, (int) this.maxFloorSize);
        final int roomSize = RANDOM.nextInt((int) this.minRoomSize, (int) this.maxRoomSize);
        this.generateFloorStructure(floorSize);
        this.generateEffectiveRooms(roomSize);
    }

    /**
     * fullfill the boolean matrix rapresenting the structure of the floor.
     * using a random walk
     * 
     * @param floorSize floor size
     */
    private void generateFloorStructure(final int floorSize) {
        final List<FloorStructureGenerationStrategy> generations = List.of(
                new FloorStructureGenerationRandomWalk(
                        (int) Math.floor((double) floorSize / 2), floorSize - 1)
        // new FloorStructureGenerationFullfill()
        );
        this.floorMap = generations.get(RANDOM.nextInt(generations.size())).generate(floorSize);
    }

    /**
     * fullfill the sets that rapresent the rooms.
     * 
     * @param roomSize size of the room
     * @throws IOException if link for the rooms json doesnt exist
     */
    private void generateEffectiveRooms(final int roomSize) throws IOException {
        final int maxEnemyInARoom = 2;
        final int minEnemyInARoom = 1;
        final int maxCollectablesInARoom = 3;
        final int minCollectablesInARoom = 1;
        RoomBuilder builder = new RoomBuilderImpl();
        builder = builder.insertRoomSize(roomSize);
        for (int i = 0; i < floorMap.size(); i++) {
            for (int j = 0; j < floorMap.size(); j++) {
                if (isARoom(j, i)) {
                    if (isStartRoom(j, i)) {
                        builder = builder.insertGenerationStrategy(new RoomGenerationEmpty())
                                .insertNumberOfEnemy(0)
                                .insertNumberOfCollectables(0)
                                .insertPassages(
                                        isARoom(j, i - 1),
                                        isARoom(j + 1, i),
                                        true, // passage for the exit
                                        isARoom(j - 1, i));
                        setStartPosition(j, i, roomSize);
                        setExitPosition(j, i, roomSize);
                    } else {
                        builder = builder.insertGenerationStrategy(
                                this.generationsStrategy.get(RANDOM.nextInt(this.generationsStrategy.size())))
                                .insertNumberOfEnemy(RANDOM.nextInt(minEnemyInARoom, maxEnemyInARoom))
                                .insertNumberOfCollectables(
                                        RANDOM.nextInt(minCollectablesInARoom, maxCollectablesInARoom))
                                .insertPassages(
                                        isARoom(j, i - 1),
                                        isARoom(j + 1, i),
                                        isARoom(j, i + 1),
                                        isARoom(j - 1, i));
                    }
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
        exit = new HashSet<>();
        exit.add(new Exit(tmpX, tmpY));
        exit.add(new Exit(tmpX - 1, tmpY));
    }

    /**
     * checks if a room is located in a certain location.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return true if is a room
     */
    private boolean isARoom(final int x, final int y) {
        if (x < 0 || y < 0 || x >= this.floorMap.size() || y >= this.floorMap.size()) {
            return false;
        } else {
            return floorMap.get(x).get(y);
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
        return x == (int) Math.floor((double) floorMap.size() / 2) && y == floorMap.size() - 1;
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
        final Set<GameObject> tmpStruct = room.getStructure();
        tmpStruct.forEach((w) -> w.setPosition(new Point(w.getPosition().getX() + roomX * roomSize,
                w.getPosition().getY() + roomY * roomSize)));
        this.floorStructure.addAll(tmpStruct);

        final Set<Collectable> tmpCollectables = room.getCollectables();
        tmpCollectables.forEach((w) -> w.setPosition(new Point(w.getPosition().getX() + roomX * roomSize,
                w.getPosition().getY() + roomY * roomSize)));
        this.floorCollectables.addAll(tmpCollectables);

        final Set<Enemy> tmpEnemies = room.getEnemies();
        tmpEnemies.forEach((w) -> w.setPosition(new Point(w.getPosition().getX() + roomX * roomSize,
                w.getPosition().getY() + roomY * roomSize)));
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
    public Floor copyFloor() {
        return new FloorImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxFloorSize() {
        return this.maxFloorSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMinFloorSize() {
        return this.minFloorSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxRoomSize() {
        return this.maxRoomSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMinRoomSize() {
        return this.minRoomSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getExit() {
        return new HashSet<>(this.exit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnemies(final Set<Enemy> enemies) {
        this.floorEnemies = new HashSet<>(enemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCollectables(final Set<Collectable> passedCollectables) {
        this.floorCollectables = new HashSet<>(passedCollectables);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoomGenerationStrategy> getGenerationsStrategy() {
        return new ArrayList<>(this.generationsStrategy);
    }
}
