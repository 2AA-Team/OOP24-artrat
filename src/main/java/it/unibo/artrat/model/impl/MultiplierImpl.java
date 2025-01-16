package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Multiplier.
 * @author Cristian Di Donato.
 */
public class MultiplierImpl implements Multiplier {

    private double multipler=0.0;

    @Override
    public double multipleTheCoins(double coins) {
        if(coins>=0.0){
            return coins*multipler;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void changeCurrentMultiplier(double multipler) {
        try{
            this.multipler = multipler;
        } catch (Exception e){
            // Se il multipler passato per qualsiasi motivo non funziona oppure non è giusto.
            throw new IllegalArgumentException();
        }
    }
    
}
