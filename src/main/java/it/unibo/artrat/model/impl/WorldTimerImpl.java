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
    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run(){

            }
        }, DEFAULT_TIMER_SETUP);
    }

    @Override
    public void ResetTimer() {
        if(timer != null){
            timer.cancel();
        }
    }

    @Override
    public boolean isTimeOut() {
        return outOfTime;
    }
    
}
