package it.unibo.artrat.utils.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import it.unibo.artrat.utils.api.ResourceLoader;

/**
 * resource loader for yaml file.
 * 
 * @param <I> input type
 * @param <O> output type
 * @author Matteo Tonelli
 */
public final class ResourceLoaderImpl<I, O> implements ResourceLoader<I, O> {

    private Map<I, O> obj = new HashMap<>();

    private ResourceLoaderImpl(final ResourceLoaderImpl<I, O> rl) {
        this.obj = new HashMap<>(rl.obj);
    }

    /**
     * empty constructor.
     */
    public ResourceLoaderImpl() {
        this.obj = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConfigPath(final InputStream configPath) {
        try (configPath) {
            final Yaml yaml = new Yaml();
            this.obj = Map.copyOf(yaml.load(configPath));
        } catch (IOException e) {
            throw new IllegalStateException("the given path is invalid", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public O getConfig(final I conf) {
        final Object ob = obj.get(conf);
        if (ob != null) {
            return obj.get(conf);
        } else {
            throw new IllegalStateException("This conf doesn't exist.");
        }
    }

    @Override
    public ResourceLoader<I, O> getClone() {
        return new ResourceLoaderImpl<>(this);
    }
}
