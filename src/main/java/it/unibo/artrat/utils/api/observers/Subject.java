package it.unibo.artrat.utils.api.observers;

import it.unibo.artrat.model.api.Model;

/**
 * interface that describe osservable class.
 */
public interface Subject {

    /**
     * notify all observer (of this subject).
     */
    void notifyOb(final Model model);

    /**
     * add a new observer to this subject.
     * 
     * @param ob observer to add
     */
    void add(final Observer ob);

    /**
     * remove an observer from this subject.
     * 
     * @param ob observer to remove
     */
    void remove(final Observer ob);
}
