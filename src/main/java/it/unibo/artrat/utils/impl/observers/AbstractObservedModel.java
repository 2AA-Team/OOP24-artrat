package it.unibo.artrat.utils.impl.observers;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.utils.api.observers.Observer;
import it.unibo.artrat.utils.api.observers.Subject;

/**
 * abstract implementation of subject.
 */
public abstract class AbstractObservedModel implements Subject {

    Set<Observer> observers = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyOb() {
        for (final Observer observer : observers) {
            observer.update(null); // [TO-DO]
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Observer ob) {
        observers.add(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final Observer ob) {
        observers.remove(ob);
    }

}
