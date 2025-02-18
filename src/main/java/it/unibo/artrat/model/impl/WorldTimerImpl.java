package it.unibo.artrat.model.impl;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.WorldTimer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * WorldTimerImpl class.
 * @author Manuel Benagli
 */
public class WorldTimerImpl implements WorldTimer {
    private static final int DEFAULT_TIMER_SETUP = 100_000;
    private Timer timer;
    private boolean outOfTime;
    private final int countdown;
    private final StoreSubController contr;

    /**
     * 
     * @param contr Controller provvisorio passato nello store per testarlo.
     */
    public WorldTimerImpl(final StoreSubController contr) {
        this.countdown = DEFAULT_TIMER_SETUP;
        this.timer = new Timer("WorldTimer");
        this.outOfTime = false;
        this.contr = contr;
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
                contr.setStage(Stage.MENU);
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
}
