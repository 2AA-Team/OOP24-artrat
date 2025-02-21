package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.MissionType;
import it.unibo.artrat.utils.api.ItemReader;

/**
 * An implementation of ItemReader.
 */
public class ItemReaderImpl implements ItemReader {
    private Map<String, List<String>> obj = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setItemPath(final URI configPath) throws IOException {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = new FileInputStream(new File(configPath));
        this.obj = Map.copyOf(yaml.load(inputStream));
        inputStream.close();
    }

    private String getConfig(final String conf, final int field) {
        final Object ob = obj.get(conf);
        if (ob instanceof List<?> list) {
            try {
                final List<String> safeList = list.stream()
                        .map(e -> Objects.toString(e, null))
                        .toList();
                return safeList.get(field);
            } catch (IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Index out of bounds for element: " + conf);
            }
        }
        throw new IllegalArgumentException(conf + " is not valid list.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(final String nameOfItem) {
        return getConfig(nameOfItem, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final String nameOfItem) {
        return Double.parseDouble(getConfig(nameOfItem, 1));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(final String nameOfItem) {
        switch (getConfig(nameOfItem, 2)) {
            case "CONSUMABLE":
                return ItemType.CONSUMABLE;
            case "POWERUP":
                return ItemType.POWERUP;
            default:
                break;
        }
        throw new IllegalStateException("The second field in the YAML file related to "
                + nameOfItem + " is either not an ItemType or has a typo.");
    }

    @Override
    public MissionType getMissionType(final String nameOfMission) {
        switch (getConfig(nameOfMission, 2)) {
            case "CONSUMABLE":
                return MissionType.SCORE;
            case "POWERUP":
                return MissionType.CHALLENGE;
            default:
                break;
        }
        throw new IllegalStateException("The second field in the YAML file related to "
                + nameOfMission + " is either not an MissionType or has a typo.");
    }

    /**
     * s.
     */
    @Override
    public Set<String> getAllItemsName() {
        return this.obj.keySet();
    }
}
