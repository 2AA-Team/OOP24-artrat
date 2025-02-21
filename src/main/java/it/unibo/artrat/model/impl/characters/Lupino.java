package it.unibo.artrat.model.impl.characters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unibo.artrat.model.api.Collectable;
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
    private List<Collectable> collectabels;
    private Coin coins;
    private Multiplier multiplier;

    /**
     * Player constructor with default vector.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     */
    public Lupino(final Point topLeft, final Point bottomRight) {
        this(topLeft, bottomRight, new HashSet<>());
        this.inventory = new InventoryImpl();
        this.multiplier = new MultiplierImpl();
        this.coins = new CoinImpl();
        this.collectabels = new ArrayList<>();
    }

    /**
     * Player constructor.
     * 
     * @param topLeft     boundingbox corner
     * @param bottomRight boundingbox corner
     * @param v           direction
     */
    public Lupino(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight, v);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
        this.multiplier = new MultiplierImpl();
        this.collectabels = new ArrayList<>();
    }

    /**
     * Player constructor.
     * 
     * @param center center of player bounding box
     * @param width  width of player bounding box
     * @param height height of player bounding box
     * @param speed  speed of player bounding box
     */
    public Lupino(final Point center, final double width, final double height, final Set<Vector2d> speed) {
        super(center, width, height, speed);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
        this.multiplier = new MultiplierImpl();
        this.collectabels = new ArrayList<>();
    }

    /**
     * Player constructor.
     * 
     * @param center bounding box center
     * @param speed  speed of player bounding box
     */
    public Lupino(final Point center, final Set<Vector2d> speed) {
        super(center, speed);
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
        this.multiplier = new MultiplierImpl();
        this.collectabels = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory getInventory() {
        return new InventoryImpl(this.inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventory(final Inventory inventory) {
        this.inventory = new InventoryImpl(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Coin getCoin() {
        return new CoinImpl(this.coins);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCoin(final Coin coins) {
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
    public Multiplier getMultiplier() {
        return new MultiplierImpl(this.multiplier);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMultipler(final Multiplier multipler) {
        if (multipler.getCurrentMultiplier() >= 0.0) {
            this.multiplier = new MultiplierImpl(multipler);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Collectable> getColletableList() {
        return new ArrayList<>(this.collectabels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setColletableList(final List<Collectable> passedCollecatble) {
        this.collectabels = new ArrayList<>(passedCollecatble);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCollectable(final Collectable passedCollectable) {
        this.collectabels.add(passedCollectable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseMultiplier(final double mutiple) {
        this.multiplier.changeCurrentMultiplier(this.multiplier.getCurrentMultiplier() * mutiple);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player copyPlayer() {
        final Player p = new Lupino(this.getPosition(), this.getSpeed());
        p.setBoost(this.getBoost());
        p.setInventory(this.getInventory());
        p.setCoin(this.getCoin());
        p.setMultipler(this.getMultiplier());
        p.setColletableList(this.getColletableList());
        return p;
    }
}
