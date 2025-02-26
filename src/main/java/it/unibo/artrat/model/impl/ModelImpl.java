package it.unibo.artrat.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.missioncenter.MissionCenter;
import it.unibo.artrat.model.api.shop.Shop;
import it.unibo.artrat.model.api.world.Floor;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.missioncenter.MissionCenterImpl;
import it.unibo.artrat.model.impl.shop.ShopImpl;
import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.impl.world.FloorImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {
    private Player player;
    private Shop shop;
    private List<Mission> missions;
    private Floor floor;

    /**
     * Permit to create a new istance of Model.
     */
    public ModelImpl() {
        this.player = new Lupino(new Point(), new Point());
        this.shop = new ShopImpl();
        final MissionCenter missionCenter = new MissionCenterImpl();
        this.floor = new FloorImpl();
        this.shop.initShop();
        missionCenter.initMissionCenter();
        missions = missionCenter.getMissionList();

    }

    /**
     * Permit to create a new istance of model, starting from the passed one.
     * 
     * @param m the passed Model.
     */
    public ModelImpl(final Model m) {
        this.player = m.getPlayer();
        this.shop = m.getShop();
        this.missions = m.getMissions();
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
     * {@inheritDoc}
     */
    @Override
    public Shop getShop() {
        return new ShopImpl(shop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShop(final Shop shop) {
        this.shop = new ShopImpl(shop);
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
