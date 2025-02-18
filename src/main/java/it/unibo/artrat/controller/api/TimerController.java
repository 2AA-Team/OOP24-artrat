package it.unibo.artrat.controller.api;

/**
 * @author Manuel Benagli
 * TimerController interface.
 */
public interface TimerController {

    /**
     * Timer starts if I play a new game.
     */
    void startTimerController();

    /**
     * Timer resets if the player get caught or time is out.
     */
    void resetTimerController();

    /**
     * Timer stops if I enter in the inventory during the game.
     */
    void stopTimerController();

    /**
     * method which get the current time. 
     */
    void getCurrentTimeController();        //DEVO CHIAMARLA O AGGIORNARLA OGNI SECONDO (1000 MS)

    /**
     * Timer restarts if I exit from my inventory.
     */
    void restartTimerController();

    /**
     * With this method you can modify your countdown (adding or cutting it).
     * This method can only be used in the inventory,                   SDVSEDSEWEOIGAERIBUHERGOIHEARGIUHAER
     */
    void setCountdownController();       //aggiunge o toglie tempo
    //IMPORTANTE NON PUOI USARE UN ITEM SE TI FA SCADERE IL TEMPO (GESTISCE DIDO)
    //DA CAPIRE SE PUOI AGGIUNGERE O TOGLIERE TEMPO ANCHE A INIZIO GAME
}
