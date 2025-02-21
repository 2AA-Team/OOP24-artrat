package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.MissionType;

/**
 * Abstract mission class.
 */
public abstract class AbstractMission implements Mission {
    private final MissionType missionType;
    private final String missionText;
    private final double reward;
    
    /**
     * Costruttore che inizializza una nuova istanza di Mission con variabili passate.
     * @param desc La descrizione della missione
     * @param reward Il premio della missione
     * @param missionType Il tipo della missione
     */
    protected AbstractMission(final String missionText, final double reward, final MissionType missionType) {
        this.reward = reward;
        this.missionText = missionText;
        this.missionType = missionType;
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
    public MissionType getMissionType(){
        return this.missionType;   
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMissionDone(){
        return true;
    }
}
