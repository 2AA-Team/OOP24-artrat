package it.unibo.artrat.model.api;

/**
 * Timer interface
 * @author Manuel Benagli
 */
public interface WorldTimer {

    void startTimer();

    void ResetTimer();

    boolean isTimeOut();
    
}