package it.unibo.artrat.model.impl.characters;

import it.unibo.artrat.model.api.characters.Coin;
import it.unibo.artrat.model.api.characters.Multiplier;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.impl.inventory.InventoryImpl;

/**
 * An implementation of player interface.
 */
public class PlayerImpl implements Player{

    private Inventory inventory;
    private Coin coins;

    public PlayerImpl() {
        this.inventory = new InventoryImpl();
        this.coins = new CoinImpl();
    }

    public PlayerImpl(final Player p) {
        this.setInventory(new InventoryImpl(p.getInventory())); //Si mette il new per evitare il passaggio per riferimento
        this.setCoin(new CoinImpl(p.getCoin()));
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void interact() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

    @Override
    public Inventory getInventory() {
        return new InventoryImpl(this.inventory);
    }

    @Override
    public void setInventory(final Inventory inventory) {
        this.inventory = new InventoryImpl(inventory);
    }

    @Override
    public Coin getCoin() {
        return new CoinImpl(this.coins);
    }

    @Override
    public void setCoin(final Coin coins) {
        this.coins = new CoinImpl(coins);
    }

    @Override
    public void increaseCoins(final double coins) {
        this.coins.addCoins(coins);
    }

    @Override
    public void increaseMultiplier(final double mutiple) {
        final Multiplier mp = new MultiplierImpl(this.coins.getCurrentMultiplier());
        mp.changeCurrentMultiplier(mp.getCurrentMultiplier()*mutiple);
        coins.changePlayerMultipler(new MultiplierImpl(mp));
    }
    
}
