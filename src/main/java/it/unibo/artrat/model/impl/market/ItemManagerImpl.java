package it.unibo.artrat.model.impl.market;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import it.unibo.artrat.model.api.inventory.*;
import it.unibo.artrat.model.api.market.ItemManager;

/**
 *  The model implementation of ItemManager
 */
public class ItemManagerImpl implements ItemManager{
    
    private final List<Item> itemList;

    // Costruttore per collegare il Market
    public ItemManagerImpl(List<Item> market) {
        this.itemList = market;
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public List<Item> sortItemPrice(int dir){
        Comparator<Item> sortingDir = Comparator.comparing(Item::getPrice);
        if(dir == 0){
            sortingDir = sortingDir.reversed();
        }
        return itemList.stream()
            .sorted(sortingDir)
            .collect(Collectors.toList());
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public List<Item> filterItems(ItemType itemType){
        return itemList.stream()
            .filter(it -> it.getType().equals(itemType))
            .collect(Collectors.toList());
    }

    /**
     * {@InheritDoc}
     */
    @Override
    public List<Item> searchItem(String nameToSearch){
        return itemList.stream()
            .filter(it -> it.getClass().getSimpleName().toLowerCase().startsWith(nameToSearch.trim().toLowerCase()))
            .collect(Collectors.toList());
    }
}

