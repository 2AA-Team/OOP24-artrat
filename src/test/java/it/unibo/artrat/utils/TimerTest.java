package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * Junit Test for the timer.
 * testTimer, testStopTimer, testResetTimer, testIsTimeOut
 * @author Manuel Benagli
 */
class TimerTest {
    static final int ONE_SECOND = 1000;
    private WorldTimerImpl timer;
    private int defTimer;      

    /**
     * testTimer setup, with a default setup of 5 seconds.
     * defTimer it's not final because you can modify the current time in WorldTimerImpl.
     */
    @BeforeEach
    void testTimerSetup() {
        timer = new WorldTimerImpl();
        this.defTimer = timer.getCurrentTime();
    }

    /**
     * Timer test.
     * The timer starts, and I use thread sleep for 3 seconds,
     * assertEquals expects 2 seconds remaining (timer starts from 5 seconds).
     * @throws InterruptedException 
     */
    @Test
    void testTimer() throws InterruptedException {
        final int threadSleep = 3000;
        timer.startTimer();
        Thread.sleep(threadSleep);
        assertEquals(defTimer-threadSleep, timer.getCurrentTime());
    }

    /**
     * The timer starts and keep going for n seconds, then it stops.
     * The thread sleeps another n seconds, but not the timer.
     * @throws InterruptedException if something goes wrong.
     */
    @Test
    public void testStopTimer() throws InterruptedException {
        final int threadSleep = 2000;
        timer.startTimer();
        Thread.sleep(threadSleep);
        timer.stopTimer();
        assertTrue(timer.isPaused());
        assertEquals(defTimer - threadSleep, timer.getCurrentTime());
        Thread.sleep(threadSleep);
        assertEquals(defTimer - threadSleep, timer.getCurrentTime());
    }

    /**
     * test timer reset.
     * Thread sleep for n seconds, then the timer is set to defValue
     * @throws InterruptedException
     */
    @Test
    public void testResetTimer() throws InterruptedException {
        final int threadSleep = 2000;
        timer.startTimer();
        Thread.sleep(threadSleep);
        timer.resetTimer(); 
        assertEquals(defTimer, timer.getCurrentTime());
    }

    /**
     * test time is out
     * The timer starts, Thread sleep for defTimer/2,
     *  then assertion (the countdown is not over).
     * Thread sleep for defTimer/2
     * 
     * @throws InterruptedException if the status is not true
     */
    @Test
    public void testIsTimeOut() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(defTimer/2);
        assertFalse(timer.isTimeOut());
        Thread.sleep(2+defTimer/2); 
        assertTrue(timer.isTimeOut());
        /*
        timer.resetTimer();
        timer.startTimer();
        Thread.sleep(defTimer/2);
        timer.resetTimer();
        assertTrue(timer.isTimeOut());
        */ 
    }

    
    /**
     * I can add or cut time only in my inventory during the game.
     * The timer is always stopped when I am in the inventory.
     * @throws InterruptedException
     */
    
    public void testSetCountdown() {
        timer.setCountdown(2000); // Aggiungi 2 secondi al countdown

        // Verifica che il tempo iniziale sia aumentato correttamente
     //   assertEquals(7000, timer.getCurrentTime());

        /**
         * //magari da aggiungere un thread sleep per sicurezza
        timer.startTimer();
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        Thread.sleep(ONE_SECOND);
        timer.stopTimer();
        assertTrue(timer.isPaused());
        timer.setCountdown(5000);
        //assertEquals(timer.getCurrentTime(), timer.getCurrentTime()+5000);
        timer.startTimer();
         */
    }
}
