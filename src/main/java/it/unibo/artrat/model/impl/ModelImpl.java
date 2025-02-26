package it.unibo.artrat.model.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.app.ArtRat;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.api.missioncenter.MissionCenter;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.model.impl.market.MarketImpl;
import it.unibo.artrat.model.impl.missioncenter.MissionCenterImpl;
import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.api.ResourceLoader;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtRat.class);
    private Player player;
    private Market market;
    private List<Mission> missions;
    private Floor floor;

    /**
     * Permit to create a new istance of Model.
     * 
     * @param rl resource loader for config file
     */
    public ModelImpl(final ResourceLoader<String, Double> rl) {
        this.player = new Lupino(new Point(), new Point());
        this.market = new MarketImpl();
        final MissionCenter missionCenter = new MissionCenterImpl();
        try {
            this.floor = new FloorImpl(rl);
            this.floor.generateFloorSet();
        } catch (IOException e) {
            LOGGER.warn("Error in the floor generation.");
        }
        this.market.initMarket();
        missionCenter.initMissionCenter();
        missions = missionCenter.getMissionList();

    }

    /**
     * Permit to create a new istance of model, starting from the passed one.
     * 
     * @param m the passed Model.
     */
    public ModelImpl(final Model m) {
        if (m != null) {
            this.player = m.getPlayer();
            this.market = m.getMarket();
            this.missions = m.getMissions();
            this.floor = m.getFloor();
        }
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
     * {@inheritDoc}
     */
    @Override
    public Market getMarket() {
        return new MarketImpl(market);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMarket(final Market market) {
        this.market = new MarketImpl(market);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mission> getMissions() {
        return new ArrayList<>(this.missions);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMissions(final List<Mission> passedMissions) {
        this.missions = new ArrayList<>(passedMissions);
    }
}
