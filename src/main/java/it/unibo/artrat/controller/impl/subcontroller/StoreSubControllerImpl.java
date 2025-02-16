package it.unibo.artrat.controller.impl.subcontroller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

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
import it.unibo.artrat.model.impl.characters.PlayerImpl;
import it.unibo.artrat.model.impl.market.ItemManagerImpl;
import it.unibo.artrat.model.impl.market.MarketImpl;
import it.unibo.artrat.view.api.MarketView;
import it.unibo.artrat.view.impl.MarketSubPanel;

/**
 * implementation of the sub controller for the store.
 * @author Manuel Benagli
 */
public class StoreSubControllerImpl extends AbstractSubController implements StoreSubController {

    private final MarketView marketView;
    /**
     * constructor to initialize mainController.
     * 
     * @param mainController main controller
     */
    public StoreSubControllerImpl(final MainControllerImpl mainController) {
        super(mainController);
        this.marketView = new MarketSubPanel(this);
    }

    @Override
    public List<Item> purchasableItems() {
        return new ArrayList<>(this.getModel().getMarket().getPurchItems());
    }

    @Override
    public boolean buyItem(Item itemToBuy) {        //moementaneo, mi serve il read per capire meglio
        final Model model = this.getModel();
        final Player player = model.getPlayer();
        final Market market = this.getModel().getMarket();
        final Inventory inventory = player.getInventory();
       /*   if(market.buyItem(itemToBuy)){*/
            if(player.getCoin().getCurrentAmount() >= itemToBuy.getPrice()){
                player.getCoin().spendCoins(itemToBuy.getPrice());
                inventory.addItem(itemToBuy);       //aggiungo l'item all'inventario
                player.setInventory(inventory);
                model.setMarket(new MarketImpl());
                model.setPlayer(new PlayerImpl());
                this.updateCentralizeModel(new ModelImpl(model));
                return true;
            }
        /* }*/
        return false;
    }

    @Override
    public void sorting(int choice){
       final Model model = this.getModel();
       final Market market = this.getModel().getMarket();
       final ItemManager itemMan = new ItemManagerImpl(market);

       if(choice == 1){
            
       }
       else{

       }
       model.setMarket(new MarketImpl());
    }

    @Override
    public void filterCategory(ItemType type){
        final Model model = this.getModel();
        final Market market = this.getModel().getMarket();
        final ItemManager itemMan = new ItemManagerImpl(market);
        List<Item> itemModList = itemMan.filterItems(type);
        market.setPurchItems(itemModList);
        model.setMarket(market);        //aggiorna il market
    }

    @Override
    public void searchItem(String nameToSearch){
        final Model model = this.getModel();
        final Market market = this.getModel().getMarket();
        final ItemManager itemMan = new ItemManagerImpl(market);
        List<Item> itemModList = itemMan.searchItem(nameToSearch);
        market.setPurchItems(itemModList);      
        model.setMarket(market);
    }

    @Override
    public boolean getPlayerCash(){
        return this.getModel().getPlayer().getCoin() != null;       //non giusto
    }
    
    /**
     * 
     * @param passedItem the item that we want the typeName.
     * @return the type name of desired item.
     */
    @Override
    public String getItemName(Item passedItem){
        return passedItem.getClass().getSimpleName();
    }

    /**
     * 
     * @param passedItem the item that we want the typeName.
     */
    @Override
    public void getDescription(Item passedItem){
        marketView.showMessage(this.purchasableItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(Item::getDescription)
            .findAny().get(), "Descrizione oggetto acquistabile");
    }

    @Override
    public String getTypeName(Item passedItem) {
        return this.getModel().getMarket().getPurchItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(el -> el.getType())
            .findAny().get().toString();
    }

    @Override
    public double getItemPrice(Item passedItem) {
        return this.getModel().getMarket().getPurchItems().stream()
            .filter(it -> it.equals(passedItem))
            .map(pr -> pr.getPrice())
            .findAny().get();
    }
}
