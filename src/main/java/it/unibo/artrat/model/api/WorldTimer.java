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
     * The timer will be resetted, and coming back to menu.
     * If the player get caught, if he completes the level, or the countdown is over.
     */
    void resetTimer();

    /**
     * A boolean which states if the time is out.
     * @return true if the time is over.
     */
    boolean isTimeOut();

    /**
     * 
     * @return the remaining time.
     */
    int getCurrentTime();

}
