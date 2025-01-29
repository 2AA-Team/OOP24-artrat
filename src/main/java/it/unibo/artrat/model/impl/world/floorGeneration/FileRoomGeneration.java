package it.unibo.artrat.model.impl.world.floorGeneration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import it.unibo.artrat.model.api.GameObject;
import it.unibo.artrat.model.api.world.floorGeneration.RoomGeneration;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.ResourceLoaderImpl;

public class FileRoomGeneration implements RoomGeneration {

    Map<Integer, List<Set<GameObject>>> mazeMap = new HashMap<>();

    public FileRoomGeneration(String filePath) {
        loadMazes(filePath);
    }

    @Override
    public Set<GameObject> generateRoom(int size, Point start) {
        return null;
    }

    @SuppressWarnings("unchecked")
    private void loadMazes(String filePath) {
        try {
            Map<String, List<Map<String, List<String>>>> mazesBySize;
            ResourceLoader resourceLoader = new ResourceLoaderImpl();
            System.out.println(filePath);
            resourceLoader.setConfigPath(filePath);

            mazesBySize = (Map<String, List<Map<String, List<String>>>>) resourceLoader
                    .getConfig("mazes");

            for (var entry : mazesBySize.entrySet()) {
                List<char[][]> mazeList = new ArrayList<>();

                for (Map<String, List<String>> mazeEntry : entry.getValue()) {
                    List<String> mazeLines = mazeEntry.get("maze");
                    mazeList.add(convertToCharArray(mazeLines));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private char[][] convertToCharArray(List<String> mazeLines) {
        int size = mazeLines.size();
        char[][] maze = new char[size][size];
        for (int i = 0; i < size; i++) {
            maze[i] = mazeLines.get(i).toCharArray();
        }
        return maze;
    }
}
