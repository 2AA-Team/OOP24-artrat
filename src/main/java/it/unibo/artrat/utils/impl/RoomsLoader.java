package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * class to load room from json file.
 */
public class RoomsLoader implements ResourceLoader<Integer, char[][]> {

    private Map<Integer, List<char[][]>> roomsMap = new HashMap<>();
    private final Random rd = new Random();

    /**
     * get a random room with a determinated size.
     * 
     * @param conf String of the size
     * @return char maze representing the room, null if doesnt exist a premade room
     *         with that size
     */
    @Override
    public char[][] getConfig(final Integer conf) {
        final int size = conf;
        final List<char[][]> roomsTmp = roomsMap.getOrDefault(size, List.of());
        if (!roomsTmp.isEmpty()) {
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
    public void setConfigPath(final URI configPath) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final Map<String, List<Map<String, Object>>> jsonMap = objectMapper.readValue(
                new File(configPath), new TypeReference<>() {
                });
        this.roomsMap = new HashMap<>();
        final List<Map<String, Object>> rooms = jsonMap.get("rooms");
        if (rooms == null) {
            throw new IllegalArgumentException("Error: rooms file doesnt contains rooms record.");
        }
        for (final Map<String, Object> entry : rooms) {
            final Integer size = (entry.get("size") instanceof Number) ? ((Number) entry.get("size")).intValue() : null;
            if (size == null) {
                throw new IllegalArgumentException("Error: rooms file doesnt contains size record.");
            }
            final List<List<String>> layouts = (List<List<String>>) entry.get("layouts");
            if (layouts == null || layouts.isEmpty()) {
                throw new IllegalArgumentException("Error: rooms file doesnt contains layoutrs record.");
            }
            final List<char[][]> roomsList = new ArrayList<>();
            for (final List<String> layout : layouts) {
                final int rows = layout.size();
                final int cols = layout.get(0).length();
                final char[][] roomMatrix = new char[cols][rows];
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        roomMatrix[j][i] = layout.get(i).charAt(j);
                    }
                }
                roomsList.add(roomMatrix);
            }
            roomsMap.put(size, roomsList);
        }

    }

}
