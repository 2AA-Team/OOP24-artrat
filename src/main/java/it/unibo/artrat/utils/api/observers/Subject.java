package it.unibo.artrat.utils.api.observers;

/**
 * interface that describe osservable class.
 */
public interface Subject {

    /**
     * notify all observer (of this subject).
     */
    void notifyOb();

    /**
     * add a new observer to this subject.
     * 
     * @param ob observer to add
     */
    void add(Observer ob);

    /**
     * remove an observer from this subject.
     * 
     * @param ob observer to remove
     */
    void remove(Observer ob);
}
