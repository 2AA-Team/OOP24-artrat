package it.unibo.artrat.utils.api.observers;

import it.unibo.artrat.model.api.Model;

/**
 * interface that describe the observer.
 */
public interface Observer {

    /**
     * update the model with a new param.
     * 
     * @param o new parameter
     */
    void update(Model o);
}
