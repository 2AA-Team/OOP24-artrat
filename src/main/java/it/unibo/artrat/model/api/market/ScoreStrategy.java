package it.unibo.artrat.model.api.market;

/**
 * An interface to calculate the score every end of a floor 
 * it was used a strategy pattern
 */

public interface ScoreStrategy {

    double calculateScore();  /*lo score si calcola in base al tempo, oggetti e numero ecc */
}
