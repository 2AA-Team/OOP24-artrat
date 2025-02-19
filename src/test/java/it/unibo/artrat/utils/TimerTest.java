package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * Test for the timer.
 * @author Manuel Benagli
 */
class TimerTest {
    static final int ONE_SECOND = 1000;
    private WorldTimerImpl timer;

    void testTimerSetup(){
        timer = new WorldTimerImpl();
    }

    void testTimer() {
        timer.startTimer();
        while (!timer.isTimeOut()) {        //isTimeOut sta nel model, è un semplice boolean di controllo
            try {
                Thread.sleep(ONE_SECOND);       //Uso il thread per aspettare 1 secondo
            } catch (InterruptedException e) {
                fail();                     
            }
        }
    }

    /**
    * resetting timer test.
    * @throws InterruptedException 
    */     
    void testResettingTimer() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        timer.resetTimer();
        assertFalse(timer.isTimeOut());

        //IL RESET TIMER DEVE AVERE COME TEST PURE IL GAME OVER PER CATTURA
    }

    /**
     * 
     * @throws InterruptedException
     */
    void testStopAndGoTimer() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        timer.stopTimer();
        assertTrue(timer.isPaused());      //verifica se si sia stoppato
        Thread.sleep(ONE_SECOND);
        timer.startTimer();
        assertFalse(timer.isPaused());   //verifica che sia ripartito
    }


    /**
     * I can add or cut time only in my inventory during the game.
     * The timer is always stopped when I am in the inventory.
     * @throws InterruptedException
     */
    void testAddAndCutTime() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        timer.stopTimer();
        //magari da aggiungere un thread sleep per sicurezza
        //timer.setCountdown();
        assertTrue(null);   //verifica che sia ripartito
        //timer.setCountdown();
        assertTrue(null);   //verifica che sia ripartito
        timer.startTimer();
    }

}
