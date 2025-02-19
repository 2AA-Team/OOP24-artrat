package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.WorldTimer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * WorldTimerImpl class.
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 5000; // Tempo iniziale
    private static final int ONE_SECOND = 1000;
    private final Timer timer;
    private boolean isInPause;
    private int countdown;
    private boolean outOfTime;
    private int remainingTime;  // Tempo rimanente che si aggiorna ogni secondo
    private TimerTask currentTask;

    /**
     * 
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.isInPause = false;
        this.remainingTime = countdown; // Il tempo rimanente è uguale al tempo iniziale
    }

    /**
     *
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
                if(remainingTime > ONE_SECOND){
                    remainingTime -= ONE_SECOND;
                }else{
                    outOfTime = true;
                }
            }
        };
        // Riavvia il timer dal tempo rimanente
        timer.scheduleAtFixedRate(currentTask, 0, ONE_SECOND);
    }

    /**
     * 
     */
    @Override
    public void stopTimer() {
        if (!isInPause) {
            // Se il timer è in esecuzione, fermalo
            isInPause = true;
            remainingTime = countdown;  // Salva il tempo rimanente
            currentTask.cancel();  // Annulla il task corrente
            System.out.println("Timer in pausa");
        }
    }

    /**
     * @return true if during the game we switch to InventorySubPanel, false otherwise.
     */
    @Override
    public boolean isPaused() {
        return isInPause;
    }

    /**
     * 
     */
    @Override
    public int getCurrentTime() {
        remainingTime = countdown;
        return remainingTime;  // Restituisce il tempo rimanente
    }

    /**
     * The timer will be resetted, and coming back to menu.
     * If the player get caught, if he completes the level, or the countdown is over.
     */
    @Override
    public void resetTimer() {
        timer.cancel();
        countdown = DEFAULT_TIMER_SETUP;
        remainingTime = countdown;
        isInPause = false;
        System.out.println("Timer resettato");
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
