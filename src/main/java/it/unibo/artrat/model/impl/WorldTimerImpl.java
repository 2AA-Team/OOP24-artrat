package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.WorldTimer;
import it.unibo.artrat.model.api.inventory.Item;

import java.util.Timer;
import java.util.TimerTask;

/**
 * WorldTimerImpl class.
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 10000;
    private Timer timer;
    private boolean outOfTime;      //se è scaduto il tempo
    private int countdown;          //il countdown può subire modifiche nell'inventario (addTime o cutTime)
    private boolean isInPause;
    private TimerTask currenTask;           //Questo è fondamentale nel avvio/riavvio timer

    /**
     * 
     * @param contr Controller provvisorio passato nello store per testarlo.
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.outOfTime = false;
        this.isInPause = false;
    }

    /**
     * 
     */
    public WorldTimerImpl(int settedCountDown) {    //IL SETTED COUNTDOWN E' IL TEMPO IN ms SETTATO NELL'INVENTARIO
        this.countdown = settedCountDown;
        this.timer = new Timer("WorldTimer");
        this.outOfTime = false;
        this.isInPause = false;
    }

    /**
     * 
     */
    @Override
    public void startTimer() {      // qui lo starto
        System.out.println("TIMER STARTATO");
        if(!isInPause){
            currenTask = new TimerTask() { // usa il nome giusto della variabile
                @Override
                public void run() {
                    outOfTime = true;
                    //IMPORTANTE LE LOGICHE GAME OVER
                }
            };
        }
        //qui ci ficco il currentTask di prima e il countdown che si aggiorna sempre
        timer.schedule(currenTask, countdown);  
    
    }

    /**
     * 
     */
    @Override       //resetto il timer se finisco il game prima, oppure è game over
    public void resetTimer() {
        //DEVO avere la roba di tonno o sam del game over.
        if (timer != null) {
            timer.cancel(); //cancello il time
        } 
        this.countdown = DEFAULT_TIMER_SETUP;
        this.outOfTime = false;
        this.isInPause = false;
        System.out.println("TIMER RESETTATO");
    }

    /**
     * 
     * @return true if the timer is stopper (if we are in inventory during the game). 
     */
    @Override
    public boolean isPaused(){
        return isInPause;
    }

    /**
     * 
     */
    @Override           //me lo tengo per il game over, mi sarà utile anche nei test
    public boolean isTimeOut() {
        return outOfTime;
    }

    /**
     * 
     */
    @Override
    public void stopTimer() {
        if(isInPause == false && timer != null){
            isInPause = true;
            timer.cancel();
            System.out.println("TIMER STOPPATO");
        }
    }

    /**
     * 
     */
    @Override
    public int getCurrentTime() {
        return countdown;
    }

    /**
     * 
     */
    @Override
    public void setCountdown(Item passedItem) {
        //qua devo semplicemente settare il timer con un parametro o un qualcosa di Dido
    }
}
