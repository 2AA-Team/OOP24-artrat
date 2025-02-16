package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.WorldTimer;
import java.util.Timer;
import java.util.TimerTask;

public class WorldTimerImpl implements WorldTimer{
    private static final int DEFAULT_TIMER_SETUP = 120000;
    private Timer timer;
    private boolean outOfTime;
    private final int countdown;

    public WorldTimerImpl(){
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.outOfTime = false;
    }

    @Override
    public void startTimer() {      //qui lo starto
        timer.schedule(new TimerTask() {            //classe anonima TimerTask che specifico cosa deve fare
            @Override
            public void run(){
                
            }
        }, countdown);
    }

    @Override       //resetto il timer se finisco il game prima, e non lo riavvio
    public void ResetTimer() {
        if(timer != null){
            timer.cancel(); //cancello il timer
        }
    }

    @Override           //me lo tengo per il game over, mi sarà utile anche nei test
    public boolean isTimeOut() {
        return outOfTime;
    }
    
}
