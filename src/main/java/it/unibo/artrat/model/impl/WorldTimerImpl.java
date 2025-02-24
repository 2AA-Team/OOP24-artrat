package it.unibo.artrat.model.impl;

import java.util.Timer;
import java.util.TimerTask;

import it.unibo.artrat.model.api.WorldTimer;

/**
 * WorldTimer implementation class.
 * 
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 120_000;
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private boolean outOfTime;
    private int countdown;
    private int remainingTime;
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
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        outOfTime = false;
        currentTask = new TimerTask() {

            @Override
            public void run() {
                if (remainingTime > ONE_SECOND) {
                    remainingTime -= ONE_SECOND;
                } else {
                    outOfTime = true;
                    resetTimer();
                }
            }
        };
        timer.scheduleAtFixedRate(currentTask, ONE_SECOND, ONE_SECOND);
    }

    /**
     * {@inheritDoc}
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
