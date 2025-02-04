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
public class RoomsLoader implements ResourceLoader {

    private Translator translator;
    private Map<Integer, List<char[][]>> roomsMap = new HashMap<>();

    /**
     * constructor that initiate translator.
     * 
     * @param translator class to implement the translate method
     */
    public RoomsLoader(Translator translator) {
        this.translator = translator;
    }

    /**
     * get a random room with a determinated size.
     * 
     * @param conf String of the size
     * @return Object representing a set<GameObject>
     */
    @Override
    public Object getConfig(String conf) {
        int size = Integer.parseInt(conf);
        List<char[][]> roomsTmp = roomsMap.getOrDefault(size, List.of());
        if (roomsTmp.size() != 0) {
            return translator.translate(roomsTmp.get(new Random().nextInt(roomsTmp.size())));
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setConfigPath(String configPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Map<String, Object>>> jsonMap = objectMapper.readValue(new File(configPath), Map.class);
        this.roomsMap = new HashMap<>();
        for (Map<String, Object> entry : jsonMap.get("mazes")) {
            Integer size = (Integer) entry.get("size");
            List<List<String>> layouts = (List<List<String>>) entry.get("layouts");
            List<char[][]> roomsList = new ArrayList<>();
            for (List<String> layout : layouts) {
                int rows = layout.size();
                int cols = layout.get(0).length();
                char[][] roomMatrix = new char[rows][cols];
                for (int i = 0; i < rows; i++) {
                    roomMatrix[i] = layout.get(i).toCharArray();
                }
                roomsList.add(roomMatrix);
            }
            roomsMap.put(size, roomsList);
        }
    }

}
