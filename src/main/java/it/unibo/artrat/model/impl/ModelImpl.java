package it.unibo.artrat.model.impl;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.api.missioncenter.MissionCenter;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.market.MarketImpl;
import it.unibo.artrat.model.impl.missioncenter.MissionCenterImpl;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {
    private Player player;
    private Market market;
    private MissionCenter missionCenter;
    private Floor floor;

    /**
     * Permit to create a new istance of Model.
     */
    public ModelImpl() {
        this.player = new Lupino(new Point(), new Point());
        this.market = new MarketImpl();
        this.missionCenter = new MissionCenterImpl();
        this.floor = new FloorImpl();
        this.market.initMarket();
        this.missionCenter.initMissionCenter();

    }

    /**
     * Permit to create a new istance of model, starting from the passed one.
     * 
     * @param m the passed Model.
     */
    public ModelImpl(final Model m) {
        this.player = m.getPlayer();
        this.market = m.getMarket();
        this.missionCenter = m.getMissionCenter();
        this.floor = m.getFloor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player.copyPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayer(final Player player) {
        this.player = player.copyPlayer();
    }

    /**
     * s.
     */
    @Override
    public Market getMarket() {
        return new MarketImpl(market);
    }

    /**
     * s.
     */
    @Override
    public void setMarket(final Market market) {
        this.market = new MarketImpl(market);
    }
    
    /**
     * 
     * @return
     */
    @Override
    public MissionCenter getMissionCenter() {
        return new MissionCenterImpl(missionCenter);
    }

    /**
     * 
     * @return
     */
    @Override
    public void setMissionCenter(final MissionCenter missionCenter) {
        this.missionCenter = new MissionCenterImpl(missionCenter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Floor getFloor() {
        return this.floor.copyFloor();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFloor(final Floor passedFloor) {
        this.floor = passedFloor.copyFloor();
    }

}
