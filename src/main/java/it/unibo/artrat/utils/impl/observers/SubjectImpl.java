package it.unibo.artrat.utils.impl.observers;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.utils.api.observers.Observer;
import it.unibo.artrat.utils.api.observers.Subject;

public class SubjectImpl implements Subject {

    final Set<Observer> observers;

    public SubjectImpl() {
        observers = new HashSet<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyOb(final Model model) {
        for (final Observer observer : observers) {
            observer.update(model); 
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
