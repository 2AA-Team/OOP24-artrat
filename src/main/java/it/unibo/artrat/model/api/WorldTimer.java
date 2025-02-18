package it.unibo.artrat.model.api;

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
    void setCountdown();

    /**
     * If we enter in the inventory while playing, the timer stops.
     */
    void stopTimer();

    /**
     * The timer restarts when I'll exit from the inventory and I'll continue the game.
     */
    void restartTimer();

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
}
