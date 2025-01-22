package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.ScoreStrategy;

/**
 * This class is used to calculate the score every time
 * a powerup needs it.
 */
public class ScorePowerupStrategy implements ScoreStrategy{
    
    @Override
    public double calculateScore(){
        return 0;
    }
}
