package it.unibo.artrat.model.api.world;

/**
 * Room factory.
 */
public interface RoomFactory {

    /**
     * create a square room by placing block to make a maze.
     * 
     * @param size size of the side of the square
     * @return maze room
     */
    Room createMaze(int size);

    /**
     * takes a room of a certain size from a pre-established set.
     * 
     * @param size size of the side of the square
     * @return pre-made room
     */
    Room createFromSet(int size);
}
