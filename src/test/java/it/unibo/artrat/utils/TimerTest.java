package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * Test for the timer.
 * @author Manuel Benagli
 */
class TimerTest {
    static final int ONE_SECOND = 1000;
    private WorldTimerImpl timer;

    /**
     * 
     */
    @BeforeEach
    void testTimerSetup() {
        timer = new WorldTimerImpl();
    }

    /**
     * @throws InterruptedException 
     */
    @Test
    void testTimer() {
        timer.startTimer();
        while (!timer.isTimeOut()) {        //isTimeOut sta nel model, è un semplice boolean di controllo
            try {
                Thread.sleep(ONE_SECOND);       //Uso il thread per aspettare 1 secondo
            } catch (InterruptedException e) {
                fail();
            }
        }
        return;
    }

    /**
    * resetting timer test.
    * @throws InterruptedException 
    */
    @Test
    void testResettingTimer() throws InterruptedException {
        timer.getCurrentTime();
        timer.startTimer();
        timer.getCurrentTime();
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        timer.getCurrentTime();
        timer.resetTimer();
        assertFalse(timer.isTimeOut());
        timer.getCurrentTime();
        //IL RESET TIMER DEVE AVERE COME TEST PURE IL GAME OVER PER CATTURA
    }

    /**
     * 
     * @throws InterruptedException
     */
    @Test
    void testStopAndGoTimer() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        timer.stopTimer();
        assertTrue(timer.isPaused());      //verifica se si sia stoppato
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        timer.startTimer();
        assertFalse(timer.isPaused(), "Il timer non è stato riavviato correttamente.");
    }

    /**
     * I can add or cut time only in my inventory during the game.
     * The timer is always stopped when I am in the inventory.
     * @throws InterruptedException
     */
    @Test
    void testAddAndCutTime() throws InterruptedException {
        //magari da aggiungere un thread sleep per sicurezza
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        timer.stopTimer();
        assertTrue(timer.isPaused());
        timer.setCountdown(5000);
        assertEquals(timer.getCurrentTime(), timer.getCurrentTime()+5000);
        timer.startTimer();
    }
}
