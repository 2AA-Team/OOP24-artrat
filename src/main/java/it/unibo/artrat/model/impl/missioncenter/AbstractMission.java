package it.unibo.artrat.model.impl.missioncenter;

import it.unibo.artrat.model.api.missioncenter.Mission;

/**
 * Abstract mission class.
 */
public abstract class AbstractMission implements Mission {
    private final String missionText;
    private final double reward;
    private final String name;
    
    /**
     * Costruttore che inizializza una nuova istanza di Mission con variabili passate.
     * @param desc La descrizione della missione
     * @param reward Il premio della missione
     * @param missionType Il tipo della missione
     */
    protected AbstractMission(final String name, final String missionText, final double reward) {
        this.name = name;
        this.reward = reward;
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
     * 
     * @return
     */
    @Override
    public double getReward(){
        return this.reward;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(){
        return true;
    }
}
