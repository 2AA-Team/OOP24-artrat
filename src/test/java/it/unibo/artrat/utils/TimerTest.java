package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * Test for the timer.
 * @author Manuel Benagli
 */
class TimerTest {
    static final int ONE_SECOND = 1000;
    static final int DEFAULT_TIMER_SETUP = 5000;
    private WorldTimerImpl timer;

    /**
     * 
     */
    @BeforeEach
    void testTimerSetup() {
        timer = new WorldTimerImpl(DEFAULT_TIMER_SETUP);
    }

    /**
     * @throws InterruptedException 
     */
    @Test
    void testTimer() throws InterruptedException {
        timer.startTimer();
        
        Thread.sleep(3000);
        assertEquals(2000, timer.getCurrentTime());
    }

    /**
     * 
     * @throws InterruptedException
     */
    @Test
    public void testStopTimer() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(2000); // Attendi per 2 secondi

        timer.stopTimer();
        assertTrue(timer.isPaused());
        assertEquals(3000, timer.getCurrentTime());
    }

    /**
     * 
     * @throws InterruptedException
     */
    @Test
    public void testResetTimer() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(2000);

        // Resetto il timer, lupino scappa o viene catturato, il gioco termina prematuramente
        timer.resetTimer(); 
        assertEquals(DEFAULT_TIMER_SETUP, timer.getCurrentTime());
    }

    /**
     * 
     * @throws InterruptedException
     */
    @Test
    public void testIsTimeOut() throws InterruptedException {
        timer.startTimer();
        Thread.sleep(3000);
        assertFalse(timer.isTimeOut());
        Thread.sleep(6000); // Attendo per 6 secondi (più del tempo del countdown)
        assertTrue(timer.isTimeOut());  //il tempo è finito, si è resettato
    }

    
    /**
     * I can add or cut time only in my inventory during the game.
     * The timer is always stopped when I am in the inventory.
     * @throws InterruptedException
     */
    
    public void testSetCountdown() {
        timer.setCountdown(2000); // Aggiungi 2 secondi al countdown

        // Verifica che il tempo iniziale sia aumentato correttamente
        assertEquals(7000, timer.getCurrentTime());

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
