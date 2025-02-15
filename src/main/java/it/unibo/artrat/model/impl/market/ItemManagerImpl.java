package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.inventory.*;
import it.unibo.artrat.model.api.market.ItemManager;
import it.unibo.artrat.model.api.market.Market;

/**
 *  Item manager
 */
public class ItemManagerImpl implements ItemManager{
    
    private final Market market;

    // Costruttore per collegare il Market
    public ItemManagerImpl(Market market) {
        this.market = market;
    }

    @Override
    public List<Item> sortItemPrice(){
        return market.getPurchItems().stream()
            .sorted(Comparator.comparing(Item::getPrice))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> reverseSortItemPrice(){
        return market.getPurchItems().stream()
            .sorted(Comparator.comparing(Item::getPrice).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> filterItems(ItemType itemType){
        return market.getPurchItems().stream()
            .filter(it -> it.getType().equals(itemType))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItem(Item item){
        return market.getPurchItems().stream()
            .filter(it -> it.getDescription().equals(item.getDescription()))
            .collect(Collectors.toList());
    }
}

