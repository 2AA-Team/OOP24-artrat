package it.unibo.artrat.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import it.unibo.artrat.utils.api.ResourceLoader;

/***
 * Implementation of a resource loader responsible for fetching and loading
 * resources such as files, configurations, or other data types.
 */
public final class ResourceLoaderImpl implements ResourceLoader {

    private Map<String, Object> obj = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfigPath(final String configPath) throws IOException {
        final Yaml yaml = new Yaml();
        final InputStream inputStream = new FileInputStream(new File(configPath));
        this.obj = Map.copyOf(yaml.load(inputStream));
        inputStream.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getConfig(final String conf) {
        final Object ob = obj.get(conf);
        if (ob != null) {
            return obj.get(conf);
        } else {
            throw new IllegalStateException();
        }
    }

}
