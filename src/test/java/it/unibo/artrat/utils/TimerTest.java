package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.fail;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.impl.WorldTimerImpl;

/**
 * Test for the timer
 * @author Manuel Benagli
 */
class TimerTest {
    static final int SECOND = 1000; 
    StoreSubController contr;
    
    void testTimer(){       //è momentaneo
       
        WorldTimerImpl timer = new WorldTimerImpl(this.contr);
        timer.startTimer();
        while (!timer.isTimeOut()) {        //isTimeOut sta nel model, è un semplice boolean di controllo
            try {
                Thread.sleep(SECOND);       //Uso il thread per aspettare 1 secondo               
            } catch (InterruptedException e) {
                fail();                     //rivedi
            }
        }
    }

    void testResettingTimer(){
        

    }


}
