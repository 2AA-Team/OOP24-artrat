package it.unibo.artrat.model.api;

/**
 * Timer interface
 * @author Manuel Benagli
 */
public interface WorldTimer {

    /**
     * Everytime I start a new game, a new timer starts
     */
    void startTimer();

    /**
     *  If Lupino gets caught or the timer stops  
     */
    void resetTimer();

    /**
     * 
     * @return true if the time is out
     */
    boolean isTimeOut();
    
}