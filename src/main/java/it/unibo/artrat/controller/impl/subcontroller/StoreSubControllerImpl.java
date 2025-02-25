package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.api.market.ItemManager;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.market.FilterItemStrategy;
import it.unibo.artrat.model.impl.market.ItemManagerImpl;
import it.unibo.artrat.model.impl.market.SearchItemStrategy;
import it.unibo.artrat.model.impl.market.SortItemStrategy;
import it.unibo.artrat.view.api.MarketView;
import it.unibo.artrat.view.impl.MarketSubPanel;

/**
 * Implementation of the SubStoreController for the market(store).
 * 
 * @author Manuel Benagli
 */
public class StoreSubControllerImpl extends AbstractSubController implements StoreSubController {
    private final MarketView marketView;
    private final ItemManager itemMan;
    private List<Item> currenItems = new ArrayList<>();

    /**
     * Constructor to initialize mainController.
     * itemMan will be used to connect this controller into ItemManagerImpl.
     * 
     * @param mainController the main controller.
     */
    public StoreSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
        this.marketView = new MarketSubPanel(this);
        this.itemMan = new ItemManagerImpl(currenItems, new SortItemStrategy(), new FilterItemStrategy(), 
        new SearchItemStrategy());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initItemList() {
        this.currenItems = new ArrayList<>(this.getModel().getMarket().getPurchItems());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Item> purchasableItems() {
        return new ArrayList<>(currenItems);
    }

    /*
     * This private method is essential to the currect update of my item list int
     * the MarketSubPanel.
     * This method is called every time I call filterCategory and searchItem.
     * Considering that searchItem is called with with every character inserted or
     * removed, but also with every general modification (changedUpdate method),
     * the update performs at its maximum.
     */
    private void updateCurrentItem() {
        this.currenItems = new ArrayList<>(this.getModel().getMarket().getPurchItems());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyItem(final Item itemToBuy) {
        final Model model = this.getModel();
        final Player player = model.getPlayer();
        final Market market = model.getMarket();
        final Inventory inventory = player.getInventory();

        if (market.buyItem(itemToBuy)) {
            player.spendCoins(itemToBuy.getPrice());
            inventory.addItem(itemToBuy); // Adding the items in the inventory.
            player.setInventory(inventory);
            if (itemToBuy.getType().equals(ItemType.POWERUP)) {
                getModel().getMarket().getPurchItems().remove(itemToBuy);
            }
            model.setMarket(market);
            model.setPlayer(player.copyPlayer());
            this.updateCentralizeModel(new ModelImpl(model));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sorting(final int choice) {
        this.itemMan.updateItemList(currenItems);
        currenItems = new ArrayList<>(itemMan.sortItemPrice(choice));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void filterCategory(final ItemType type) {
        updateCurrentItem();
        this.itemMan.updateItemList(currenItems);
        currenItems = new ArrayList<>(itemMan.filterItems(type));
        this.itemMan.updateItemList(currenItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void searchItem(final String nameToSearch) {
        updateCurrentItem();
        this.itemMan.updateItemList(currenItems);
        currenItems = new ArrayList<>(itemMan.searchItem(nameToSearch));
        this.itemMan.updateItemList(currenItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getItemName(final Item passedItem) {
        return passedItem.getClass().getSimpleName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showDescription(final Item passedItem) {
        marketView.showMessage(this.purchasableItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(Item::getDescription)
            .findAny().get(), "Purchasable item's description");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(final Item passedItem) {
        return this.getModel().getMarket().getPurchItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(Item::getType)
            .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getItemPrice(final Item passedItem) {
        return this.getModel().getMarket().getPurchItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(Item::getPrice)
            .findAny().get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentAmount() {
        return this.getModel().getPlayer().getCoin().getCurrentAmount();
    }
}
