package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import it.unibo.artrat.utils.api.Reader;

/**
 * An abstract reader of the reader interface that handles the logic of 
 * loading from the file and leaves the casting policies to the extensions.
 */
public abstract class AbstractReader implements Reader {

    private Map<String, Map<String, Object>> obj = new HashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemReaderImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPath(final URI configPath) throws IOException {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = new FileInputStream(new File(configPath));
        this.obj = Map.copyOf(yaml.load(inputStream));
        inputStream.close();
    }

    private Object getConfig(final String conf, final String field) {
        final Object ob = obj.get(conf);
        if (ob == null) {
            throw new IllegalArgumentException(conf + " is null and not a valid map.");
        }

        Map<?, ?> list = (Map<?, ?>) ob;
        try {
            return list.get(field);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("ItemReaderImpl threw an error: ", e);
        }
        throw new IllegalArgumentException("Error retrieving field: " + field);
    }

    /**
     * @param conf the desidered conf in yaml.
     * @param field the desidered field for the passed conf
     * @return the desidered field.
     */
    protected String getSpecificFiled(final String conf, final String field) {
        return String.valueOf(getConfig(conf, field));
    }

    /**
     * Method that permit to obtain all name of conf in yaml.
     * @return the Set with all name of confs in yaml.
     */
    protected Set<String> getKeySetMap() {
        return this.obj.keySet();
    }
}
