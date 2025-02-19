package it.unibo.artrat.controller.impl;

import it.unibo.artrat.controller.api.TimerController;
import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * 
 */
public class TimerControllerImpl implements TimerController {
    private final WorldTimerImpl timer;

    //CHIEDERE SE, AVENDO IL TIMER DEL MODEL (OVVIAMENTE), SE LO DEVO AGGIUNGERE CON UN 
    //METODO GETTIMER() NELLA CLASSE MODEL E MODELIMPL

    /**
     * 
     */
    public TimerControllerImpl() {
        timer = new WorldTimerImpl();
    }

    /**
     * 
     */
    @Override
    public void startTimerController() {
        timer.startTimer();
    }

    /**
     * 
     */
    public void stopTimer() {
        timer.stopTimer();
    }

    /**
     * s.
     */
    @Override
    public void getCurrentTimeController() {
        this.timer.getCurrentTime();
    }

    /**
     * 
     */
    @Override
    public void resetTimerController() {
        timer.resetTimer();                 //se il timer scade comunque devo aggiornare qualcosa, è la logica del game over
    }

    /**
     * 
     */
    @Override
    public void stopTimerController() {
        this.timer.stopTimer();
    }

    /**
     * 
     */
    @Override
    public void setCountdownController() {
        throw new UnsupportedOperationException("Unimplemented method 'setCountdownController'");
    }
}
