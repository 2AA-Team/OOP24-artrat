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
    public void startTimerController() {        //devo capire meglio se devo aggiornare il model pure col timer, in teoria no
        timer.startTimer();                     //devo capire se nel model ho bisogno di metodi per il timer, essendo un elemento fuori da tutto
    }

    @Override
    public void resetTimerController() {
        timer.resetTimer();                 //se il timer scade comunque devo aggiornare qualcosa, è la logica del game over
    }
}
