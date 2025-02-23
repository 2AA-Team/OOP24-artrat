package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * Abstract mission class.
 */
public abstract class AbstractMission implements Mission {
    private final String missionText;
    private final String name;
    
    /**
     * Costruttore che inizializza una nuova istanza di Mission con variabili passate.
     * @param name il nome
     * @param desc La descrizione della missione
     */
    protected AbstractMission(final String name, final String missionText) {
        this.name = name;
        this.missionText = missionText;
    }

    /**
     * 
     * @return
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     * 
     * @return
     */
    @Override
    public String getText(){
        return this.missionText;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean isMissionDone(Player passedPlayer);
}
