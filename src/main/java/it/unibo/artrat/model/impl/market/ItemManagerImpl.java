package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.List;

import it.unibo.artrat.model.api.inventory.*;

public class ItemManagerImpl implements ItemManager{
    private List<Item> items = new ArrayList<>();

    public ItemManagerImpl(List<Item> items){
        this.items = items;
    }

    //per operare occorre Didonato con la sua classe

    @Override
    public List<Item> sortItemLevel() {
        
        throw new UnsupportedOperationException("Unimplemented method 'sortPowerupLevel'");
    }

    @Override
    public List<Item> sortItemCost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sortPowerupCost'");
    }

    @Override
    public List<Item> filterItemLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterPowerupLevel'");
    }

    @Override
    public List<Item> filterItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filterItem'");
    }
    
}
