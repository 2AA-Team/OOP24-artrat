package it.unibo.artrat.model.api;

import it.unibo.artrat.model.api.inventory.Item;

/**
 * Timer interface.
 * @author Manuel Benagli
 */
public interface WorldTimer {

    /**
     * Every time I start a new game, a new timer starts.
     */
    void startTimer();

    /**
     *  If Lupino gets caught or the timer is out.
     */
    void resetTimer();

    /**
     *  If I use an item I can add or cut time, in base of item's effect.
     */
    void setCountdown(Item itemPassed);

    /**
     * If we enter in the inventory while playing, the timer stops.
     */
    void stopTimer();

    /**
     * 
     * @return the current time (updates every second).
     */
    int getCurrentTime();

    /**
     * 
     * @return true if the time is out.
     */
    boolean isTimeOut();

    /**
     * 
     * @return true if the timer is stopped
     */
    boolean isPaused();
}
