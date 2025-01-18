package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.api.characters.Multiplier;

/**
 * An implementation of Coin.
 * @author Cristian Di Donato.
 */
public class CoinImpl implements Coin {

    private double amount;
    private Multiplier multiplier;

    @Override
    public double getCurrentAmount() {
        return this.amount;
    }

    @Override
    public void addCoins(double coins) {
        if(coins>=0.0) {
            amount+=coins;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void spendCoins(double coins) {
        if(coins>=0.0 && coins<=amount){
            amount-=coins;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Multiplier getCurrentMultiplier() {
        return this.multiplier;
    }

    @Override
    public void changePlayerMultipler(double multipler) {
        if(multipler>=0.0){
            this.multiplier.changeCurrentMultiplier(multipler);
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    
}
