package it.unibo.artrat.model.impl.characters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.characters.AbstractEntity;
import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.api.characters.Multiplier;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * Player implementation.
 * 
 * @author Samuele Trapani
 */
public class Lupino extends AbstractEntity implements Player {

    private Inventory inventory;
    private Coin coins;
    private static final Logger LOGGER = LoggerFactory.getLogger(Lupino.class);

    /**
     * Player constructor with default vector.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     */
    public Lupino(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new Vector2d());
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
    }

    /**
     * Player constructor.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     * @param v           direction
     */
    public Lupino(final Point topLeft, final Point bottomRight, final Vector2d v) {
        super(topLeft, bottomRight, v);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
    }

    /**
     * Player constructor.
     * 
     * @param center center of player bounding box
     * @param width  width of player bounding box
     * @param height height of player bounding box
     * @param speed  speed of player bounding box
     */
    public Lupino(final Point center, final double width, final double height, final Vector2d speed) {
        super(center, width, height, speed);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
    }

    /**
     * Player constructor.
     * 
     * @param center bounding box center
     * @param speed  speed of player bounding box
     */
    public Lupino(final Point center, final Vector2d speed) {
        super(center, speed);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redraw'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        LOGGER.info("Richiesta dell'attuale inventario");
        return new InventoryImpl(this.inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventory(final Inventory inventory) {
        LOGGER.info("Richiesta di set di un nuovo inventario");
        this.inventory = new InventoryImpl(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coin getCoin() {
        LOGGER.info("Richiesta dei Coin attuali");
        return new CoinImpl(this.coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoin(final Coin coins) {
        LOGGER.info("Richiesta di set di un nuovo coin");
        this.coins = new CoinImpl(coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseCoins(final double coins) {
        this.coins.addCoins(coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void spendCoins(final double coins) {
        this.coins.spendCoins(coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseMultiplier(final double mutiple) {
        final Multiplier mp = new MultiplierImpl(this.coins.getCurrentMultiplier());
        mp.changeCurrentMultiplier(mp.getCurrentMultiplier() * mutiple);
        coins.changePlayerMultipler(new MultiplierImpl(mp));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player copyPlayer() {
        final Player p = new Lupino(this.getPosition(), this.getSpeed());
        p.setInventory(this.getInventory());
        p.setCoin(this.getCoin());
        return p;
    }
}
