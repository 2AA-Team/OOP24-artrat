package it.unibo.artrat.controller.impl;

import it.unibo.artrat.controller.api.TimerController;
import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * 
 */
public class TimerControllerImpl implements TimerController{

    private  WorldTimerImpl timer;

    public TimerControllerImpl(){
        timer = new WorldTimerImpl();       //tutto provvisorio
    }

    @Override
    public void startTimerController() {
        timer.startTimer();
    }

    @Override
    public void resetTimerController() {
        timer.resetTimer();
    }


}
