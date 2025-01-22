package it.unibo.artrat.model.impl.market;

import it.unibo.artrat.model.api.market.Powerup;

public class AbstractPowerupCreator implements Powerup{
    private final String name;
    private final String descr;

    public AbstractPowerupCreator(String name, String descr){
        this.name = name;
        this.descr = descr;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public String getDescr() {
        return this.descr;
    }

    @Override
    public void apply() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
}
