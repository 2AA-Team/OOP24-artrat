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
import it.unibo.artrat.model.impl.market.ItemManagerImpl;
import it.unibo.artrat.view.api.MarketView;
import it.unibo.artrat.view.impl.MarketSubPanel;

/**
 * implementation of the sub controller for the store.
 * @author Manuel Benagli
 */
public class StoreSubControllerImpl extends AbstractSubController implements StoreSubController {
    private final MarketView marketView;
    private final ItemManager itemMan;
    private List<Item> currenItems = new ArrayList<>();

    /**
     * constructor to initialize mainController.
     * itemMan will be used to connect this controller into ItemManagerImpl.
     * @param mainController main controller
     */
    public StoreSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
        this.marketView = new MarketSubPanel(this);
        this.itemMan = new ItemManagerImpl(currenItems);
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
     * This private method is essential to the currect update of my item list int the MarketSubPanel.
     * This method is called every time I call filterCategory and searchItem.
     * Considering that searchItem is called with with every character inserted or removed,
     * BUT ALSO WITH EVERY GENERAL MODIFICATION (changedUpdate method),
     *  the update performs at its maximum
    */
    private void updateCurrentItem() {
        this.currenItems = new ArrayList<>(this.getModel().getMarket().getPurchItems());
    }

     /**
     * {@inheritDoc}
     */
    @Override
    public boolean buyItem(final Item itemToBuy) {        //moementaneo, mi serve il read per capire meglio
        final Model model = this.getModel();
        final Player player = model.getPlayer();
        final Market market = this.getModel().getMarket();
        final Inventory inventory = player.getInventory();

        if (market.buyItem(itemToBuy) && player.getCoin().getCurrentAmount() >= itemToBuy.getPrice()) {
                player.spendCoins(itemToBuy.getPrice());
                inventory.addItem(itemToBuy);       //aggiungo l'item all'inventario
                player.setInventory(inventory);
                model.setMarket(market);
                model.setPlayer(player.copyPlayer());
                this.updateCentralizeModel(new ModelImpl(model));
                updateCentralizeModel(model);
                return true;
            }
        return false;
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
            .findAny().get(), "Descrizione oggetto acquistabile");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTypeName(final Item passedItem) {
        return this.getModel().getMarket().getPurchItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(Item::getType)
            .findAny().get().toString();
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
}
