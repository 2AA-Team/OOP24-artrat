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
    static final int SETTED_COUNTDOWN = 10000;
    static final int ONE_SECOND = 1000;
    private WorldTimerImpl timer;
    private int defTimer;      

    /**
     * testTimer setup, with a default setup of 5 seconds.
     * defTimer it's not final because you can modify the current time in WorldTimerImpl.
     */
    @BeforeEach
    void testTimerSetup() {
        timer = new WorldTimerImpl(SETTED_COUNTDOWN);
        this.defTimer = SETTED_COUNTDOWN;
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
        assertEquals(defTimer-threadSleep, timer.getCurrentTime()*ONE_SECOND);
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
        assertEquals(defTimer - threadSleep, timer.getCurrentTime()*ONE_SECOND);
        Thread.sleep(threadSleep);
        assertEquals(defTimer - threadSleep, timer.getCurrentTime()*ONE_SECOND);
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
        assertEquals(defTimer, timer.getCurrentTime()*ONE_SECOND);
    }
}
