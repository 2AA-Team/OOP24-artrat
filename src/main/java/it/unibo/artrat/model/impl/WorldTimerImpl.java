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
    private static final int DEFAULT_TIMER_SETUP = 5000; // Tempo iniziale
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private int countdown;
    private boolean outOfTime;
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
     */
    public WorldTimerImpl(int settedCountdown) {
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
                    getCurrentTime();
                } else {
                    outOfTime = true;
                    resetTimer();
                }
            }
        };
        // Riavvia il timer dal tempo rimanente
        timer.scheduleAtFixedRate(currentTask, ONE_SECOND, ONE_SECOND);
        // con 1 second di settaggio prevengo problemi con eventi
        System.out.println("TIMER STARTATO");
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
        System.out.println("TIMER RESETTATO");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTimeOut() {
        return outOfTime;
    }
}
