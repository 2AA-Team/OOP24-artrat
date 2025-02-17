package it.unibo.artrat.model.impl.market;

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
    public List<Item> sortItemPrice(int dir){
        Comparator<Item> sortingDir = Comparator.comparing(Item::getPrice);
        if(dir == 0){
            sortingDir = sortingDir.reversed();
        }
        System.out.println("ITEMMMMMMMMMMMM" + market.getPurchItems().stream()
        .sorted(sortingDir)
        .collect(Collectors.toList()));

        return market.getPurchItems().stream()
            .sorted(sortingDir)
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> filterItems(ItemType itemType){
       /* System.out.println("ITEMMMMMMMMMMMM" + market.getPurchItems().stream()
        .filter(it -> it.getType().equals(itemType))
        .collect(Collectors.toList()));*/
        return market.getPurchItems().stream()
            .filter(it -> it.getType().equals(itemType))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> searchItem(String nameToSearch){
        return market.getPurchItems().stream()
            .filter(it -> it.getDescription().equals(nameToSearch))
            .collect(Collectors.toList());
    }
}

