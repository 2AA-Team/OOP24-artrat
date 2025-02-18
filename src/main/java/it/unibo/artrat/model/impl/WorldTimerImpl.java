package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.WorldTimer;
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
    /**
     * 
     * @param contr Controller provvisorio passato nello store per testarlo.
     */
    public WorldTimerImpl() {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.outOfTime = false;
    }

    /**
     * 
     */
    @Override
    public void startTimer() {      //qui lo starto
        System.out.println("TIMER STARTATO");
        timer.schedule(new TimerTask() {            //classe anonima TimerTask che specifico cosa deve fare
            @Override
            public void run() {
                outOfTime = true;       //questo a true quando ho poi finito il countdown
                //qua posso collegarmi con un messaggio di fine gioco
                

            }
        }, countdown);
    }

    /**
     * 
     */
    @Override       //resetto il timer se finisco il game prima, e non lo riavvio
    public void resetTimer() {
        //DEVO avere la roba di tonno o sam del game over.
        if (timer != null) {
            timer.cancel(); //cancello il timer
            timer = new Timer("WorldTimer"); 
            System.out.println("TIMER RESETTATO");
            outOfTime = false;
        }
    }

    /**
     * 
     */
    @Override           //me lo tengo per il game over, mi sarà utile anche nei test
    public boolean isTimeOut() {
        return outOfTime;
    }

    @Override
    public void stopTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopTimer'");
    }

    @Override
    public int getCurrentTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentTime'");
    }


    @Override
    public void restartTimer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restartTimer'");
    }

    @Override
    public void setCountdown() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCountdown'");
    }
}
