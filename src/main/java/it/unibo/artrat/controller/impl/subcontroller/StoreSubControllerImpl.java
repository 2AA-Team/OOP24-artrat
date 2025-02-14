package it.unibo.artrat.controller.impl.subcontroller;

import java.util.List;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.controller.impl.AbstractSubController;
import it.unibo.artrat.controller.impl.MainControllerImpl;
import it.unibo.artrat.model.api.Model;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.model.api.inventory.Inventory;
import it.unibo.artrat.model.api.inventory.Item;
import it.unibo.artrat.model.api.market.Market;
import it.unibo.artrat.model.impl.ModelImpl;
import it.unibo.artrat.model.impl.characters.PlayerImpl;
import it.unibo.artrat.view.api.MarketView;
import it.unibo.artrat.view.impl.MarketSubPanel;

/**
 * implementation of the sub controller for the store.
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
        return this.getModel().getMarket().getPurchItems();
    }

    @Override
    public boolean buyItem(Item itemToBuy) {
        final Model model = this.getModel();
        final Player player = model.getPlayer();
        final Market market = this.getModel().getMarket();
        final Inventory inv = player.getInventory();
        if(market.buyItem(itemToBuy)){
            inv.addItem(itemToBuy);
            

            final Player modPlayer = inv.addItem(itemToBuy);
            model.setPlayer(new PlayerImpl());
            this.updateCentralizeModel(new ModelImpl(model));
            return true;
        }
        return false;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getItemName'");
    }

    @Override
    public double getItemPrice(Item passedItem) {
        return passedItem.getPrice();
    }
}
