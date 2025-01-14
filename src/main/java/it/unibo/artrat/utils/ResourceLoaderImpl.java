package it.unibo.artrat.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/***
 * Implementation of a resource loader responsible for fetching and loading
 * resources such as files, configurations, or other data types.
 */
public class ResourceLoaderImpl implements ResourceLoader {

    private Map<String, Object> obj;

    /**
     * constructor that load all configPath data.
     * 
     * @param configPath
     *                   if configPath not represent anything:
     * @throws FileNotFoundException
     */
    public ResourceLoaderImpl(String configPath) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(new File(configPath));
        this.obj = Map.copyOf(yaml.load(inputStream));
    }

    @Override
    public Object getConfig(String conf) {
        Object ob = obj.get(conf);
        if (!ob.equals(null)) {
            return obj.get(conf);
        } else {
            throw new IllegalStateException();
        }
    }

}
