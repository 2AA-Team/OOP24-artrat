package it.unibo.artrat.model.impl.world.floorgeneration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.world.floorgeneration.RoomGeneration;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

/**
 * room generation that read pre-made maze from a yaml.
 * the yaml has to be like:
 * mazes:
 * --*size
 * ----maze:
 * ------*first line
 * ------*second line
 * ------*ecc.
 * ----maze:
 * ------*first line
 * --*size
 * ----maze:
 * ------*first line
 */
public class FileRoomGeneration implements RoomGeneration {

    private final Map<Integer, List<Set<GameObject>>> mazeMap = new HashMap<>();

    /**
     * constructor tha specify file path.
     * 
     * @param filePath path of the yaml file
     */
    public FileRoomGeneration(final String filePath) {
        loadMazes(filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> generateRoom(final int size, final Point start) {
        return Set.of();
    }

    /**
     * load all the maze from the file.
     * 
     * @param filePath path of the yaml file
     */
    @SuppressWarnings("unchecked")
    private void loadMazes(final String filePath) {
        try {
            final ResourceLoader resourceLoader = new ResourceLoaderImpl();
            resourceLoader.setConfigPath(filePath);
            final Map<String, List<Map<String, List<String>>>> mazesBySize = (Map<String, List<Map<String, List<String>>>>) resourceLoader
                    .getConfig("mazes");
            for (final var entry : mazesBySize.entrySet()) {
                final List<char[][]> mazeList = new ArrayList<>();
                for (final var mazeEntry : entry.getValue()) {
                    final List<String> mazeLines = mazeEntry.get("maze");
                    mazeList.add(convertToCharMatrix(mazeLines));
                }
                mazeMap.put(Integer.parseInt(entry.getKey()),
                        mazeList.stream().map(this::convertToSetGameObject).toList());
            }
        } catch (IOException | IllegalStateException | ClassCastException | NullPointerException e) {

        }
    }

    /**
     * converts from matrix of char to game object.
     * 
     * @param matrix the matrix of char
     * @return set of gameobjet about matrix
     */
    private Set<GameObject> convertToSetGameObject(char[][] matrix) {
        return Set.of();
    }

    /**
     * convert list of string in char matrix.
     * 
     * @param mazeLines list of lines, from upper to downer
     * @return matrix of char
     */
    private char[][] convertToCharMatrix(final List<String> mazeLines) {
        final int size = mazeLines.size();
        final char[][] maze = new char[size][size];
        for (int i = 0; i < size; i++) {
            maze[i] = mazeLines.get(i).toCharArray();
        }
        return maze;
    }
}
