package it.unibo.artrat.model.api.market;

import java.util.HashMap;
import java.util.function.ObjDoubleConsumer;

import it.unibo.artrat.model.api.world.ValuableItem;

/**
 * An interface to calculate the score every end of a floor 
 * it was used a strategy pattern
 */
public interface ScoreStrategy {

    double calculateScore();  /*lo score si calcola in base al tempo, oggetti e numero ecc */
}
