package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * implementation of resource loader for take pre-made rooms from a file.
 */
public class RoomsLoader implements ResourceLoader<Integer, char[][]> {

    private Map<Integer, List<char[][]>> roomsMap = new HashMap<>();
    private final Random rd = new Random();

    /**
     * get a random room with a determinated size.
     * 
     * @param conf String of the size
     * @return Object representing a set<GameObject>
     */
    @Override
    public char[][] getConfig(final Integer conf) {
        final int size = conf;
        final List<char[][]> roomsTmp = roomsMap.getOrDefault(size, List.of());
        if (roomsTmp.isEmpty()) {
            return roomsTmp.get(rd.nextInt(roomsTmp.size()));
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setConfigPath(final String configPath) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, List<Map<String, Object>>> jsonMap = objectMapper.readValue(new File(configPath), Map.class);
        this.roomsMap = new HashMap<>();
        for (final Map<String, Object> entry : jsonMap.get("mazes")) {
            final Integer size = (Integer) entry.get("size");
            final List<List<String>> layouts = (List<List<String>>) entry.get("layouts");
            final List<char[][]> roomsList = new ArrayList<>();
            for (final List<String> layout : layouts) {
                final int rows = layout.size();
                final int cols = layout.get(0).length();
                final char[][] roomMatrix = new char[rows][cols];
                for (int i = 0; i < rows; i++) {
                    roomMatrix[i] = layout.get(i).toCharArray();
                }
                roomsList.add(roomMatrix);
            }
            roomsMap.put(size, roomsList);
        }
    }

}
