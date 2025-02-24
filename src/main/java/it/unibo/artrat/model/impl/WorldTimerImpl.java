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
    private static final int DEFAULT_TIMER_SETUP = 120_000; // Tempo iniziale
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private boolean outOfTime;
    private int countdown;
    private int remainingTime; // Tempo rimanente che si aggiorna ogni secondo
    private TimerTask currentTask;

    /**
     * Default WorldTimerImpl constructor.
     * The countdown is defaulty setted on 2 minutes (120000 ms).
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.remainingTime = countdown; // Il tempo rimanente è uguale al tempo iniziale
    }

    /**
     * WorldTimerImpl constructor.
     * 
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

            @Override
            public void run() {
                // La logica del game over quando il timer finisce
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
            currentTask.cancel(); // Annulla il task corrente
        }
        countdown = DEFAULT_TIMER_SETUP; // Ripristina il countdown iniziale
        remainingTime = countdown; // Ripristina il tempo rimanente
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeOut() {
        final boolean tmp = outOfTime;
        outOfTime = false;
        return tmp;
    }
}
