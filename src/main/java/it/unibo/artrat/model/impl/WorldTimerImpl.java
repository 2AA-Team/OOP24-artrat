package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.WorldTimer;

import java.awt.SystemColor;
import java.util.Timer;
import java.util.TimerTask;

/**
 * WorldTimerImpl class.
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 10000; // Tempo iniziale
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private boolean isInPause;
    private int countdown;
    private boolean outOfTime;
    private int remainingTime;  // Tempo rimanente che si aggiorna ogni secondo
    private TimerTask currentTask;

    /**
     * Default WorldTimerImpl constructor.
     * The countdown is defaulty setted on 2 minutes (120000 ms).
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.isInPause = false;
        this.remainingTime = countdown; // Il tempo rimanente è uguale al tempo iniziale
    }

    /**
     * WorldTimerImpl constructor
     */
    public WorldTimerImpl(int settedCountdown) {
        this.countdown = settedCountdown;
        this.timer = new Timer("WorldTimer");
        this.isInPause = false;
        this.remainingTime = countdown; // Il tempo rimanente è uguale al tempo iniziale
    }

    /**
     * 
     */
    @Override
    public void startTimer() {
        isInPause = false;
        currentTask = new TimerTask() {
            
            @Override
            public void run() {
                // La logica del game over quando il timer finisce
                if (remainingTime > ONE_SECOND) {
                    remainingTime -= ONE_SECOND;
                    System.out.println("IN CORSO  " + remainingTime/1000);
                    getCurrentTime();
                } else {
                    outOfTime = true;
                    resetTimer();
                }
            }
        };
        // Riavvia il timer dal tempo rimanente
        timer.scheduleAtFixedRate(currentTask, ONE_SECOND, ONE_SECOND);
        //con 1 second di settaggio prevengo problemi con eventi
        System.out.println("TIMER STARTATO");
    }

    /**
     * 
     */
    @Override
    public int getCurrentTime(){
        return remainingTime;
    }

    /**
     * 
     */
    @Override
    public void stopTimer() {
        if (!isInPause) {
            isInPause = true;
            //remainingTime = countdown;  // Salva il tempo rimanente
            currentTask.cancel();  // Annulla il task corrente
            System.out.println("TIMER IN PAUSA");
        }
    }

    /**
     * This method is useful on JUnit TimerTest.
     * @return true if during the game we switch to InventorySubPanel, false otherwise.
     */
    @Override
    public boolean isPaused() {
        return isInPause;
    }

    /**
     * The timer will be resetted, and coming back to menu.
     * If the player get caught, if he completes the level, or the countdown is over.
     */
    @Override
    public void resetTimer() {
        if (currentTask != null) {
            currentTask.cancel();  // Annulla il task corrente
        }
        timer.cancel();  // Ferma il timer
        countdown = DEFAULT_TIMER_SETUP;  // Ripristina il countdown iniziale
        remainingTime = countdown;  // Ripristina il tempo rimanente
        isInPause = false;  // Torna alla modalità di gioco normale
        System.out.println("TIMER RESETTATO");
    }

    /**
     * 
     */
    @Override
    public void setCountdown(int settedItemTime) {
        countdown += settedItemTime;
        remainingTime += settedItemTime; 
    }

    /**
     * a boolean which states if the time is out.
     * @return true if the time is over.
     */
    @Override
    public boolean isTimeOut() {
        return outOfTime;
    }
}
