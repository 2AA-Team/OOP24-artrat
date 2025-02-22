package it.unibo.artrat.model.impl;

import java.util.Timer;
import java.util.TimerTask;

import it.unibo.artrat.model.api.WorldTimer;

/**
 * WorldTimerImpl class.
 * 
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 120000;
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private boolean outOfTime;
    private int countdown;
    private int remainingTime; // il Tempo rimanente e che poi uso per gettarlo ogni volta
    private TimerTask currentTask;

    /**
     * Default WorldTimerImpl constructor.
     * The countdown is defaulty setted on 2 minutes (120000 ms).
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.remainingTime = countdown;
    }

    /**
     * WorldTimerImpl constructor.
     * @param settedCountdown the new countdown is initialize with settedCountdown
     */
    public WorldTimerImpl(final int settedCountdown) {
        this.countdown = settedCountdown;
        this.timer = new Timer("WorldTimer");
        this.remainingTime = countdown;
    }

    /**
     *
     */
    @Override
    public void startTimer() {
        outOfTime = false;
        currentTask = new TimerTask() {

            /*
             * la logica di timer e game over quando il timer finisce
             */
            @Override
            public void run() {
                if (remainingTime > ONE_SECOND) {
                    remainingTime -= ONE_SECOND;
                    getCurrentTime();
                } else {
                    outOfTime = true;
                    resetTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(currentTask, ONE_SECOND, ONE_SECOND);
    }

    /**
     * 
     */
    @Override
    public int getCurrentTime() {
        return remainingTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetTimer() {
        if (currentTask != null) {
            currentTask.cancel();       
            /*mi cancello il task , (quella cosa che ho rimosso il timer cancel perchè poi ho 
            l'ho inizializzato nel maincontroller che per mio ragionamento è ultra performante sium)
            */
        }
        countdown = DEFAULT_TIMER_SETUP;
        remainingTime = countdown; 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeOut() {
        return outOfTime;
    }
}
