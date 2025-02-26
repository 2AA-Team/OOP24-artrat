package it.unibo.artrat.model.api;

/**
 * Timer interface.
 * The timer is initialized in MainController, in this way the timer can be extended and used in all stages, 
 * such as the shop, inventory or future sections of the project.
 * 
 * @author Manuel Benagli
 */
public interface WorldTimer {

    /**
     * Every time I start a new game, the timer starts.
     */
    void startTimer();

    /**
     * The timer will be resetted, and coming back to menu.
     * If the player get caught, if he completes the level, or the countdown is over.
     */
    void resetTimer();

    /**
     * A boolean which states if the time is out or not.
     * 
     * @return true if the time is over.
     */
    boolean isTimeOut();

    /**
     * Method which gets timer's current time.
     * 
     * @return the remaining time.
     */
    int getCurrentTime();

}
