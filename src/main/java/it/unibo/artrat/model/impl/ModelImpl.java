package it.unibo.artrat.model.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.characters.Lupino;
import it.unibo.artrat.model.impl.market.MarketImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * An implementation of model interface.
 */
public class ModelImpl implements Model {
    private Player player;
    private Market market;
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelImpl.class);

    /**
     * Permit to create a new istance of Model.
     */
    public ModelImpl() {
        this.player = new Lupino(new Point(), new Point());
        this.market = new MarketImpl();
        this.market.initMarket();
    }

    /**
     * Permit to create a new istance of model, starting from the passed one.
     * @param m the passed Model.
     */
    public ModelImpl(final Model m) {
        this.player = m.getPlayer();
        this.market = m.getMarket();
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public Player getPlayer() {
        LOGGER.info("Request for a copy of the player.");
        return this.player.copyPlayer();
    }

    /**
     * {@inheritDoc}
    */
    @Override
    public void setPlayer(final Player player) {
        LOGGER.info("Setting up the new player.");
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
}
