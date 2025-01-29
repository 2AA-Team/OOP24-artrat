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
        for (Observer observer : observers) {
            observer.update(null); // [TO-DO]
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Observer ob) {
        observers.add(ob);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(Observer ob) {
        observers.remove(ob);
    }

}
