package it.unibo.artrat.model.impl.market;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import it.unibo.artrat.model.api.inventory.*;
import it.unibo.artrat.model.api.market.ItemManager;
import it.unibo.artrat.model.api.market.Mission;

public class ItemManagerImpl implements ItemManager{
    private List<Item> items = new ArrayList<>();

    public ItemManagerImpl(List<Item> items){
        this.items = items;
    }

    @Override
    public List<Item> sortItemPrice(){
        return items.stream()
            .sorted(Comparator.comparing(Item::getPrice))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> reverseSortItemPrice(){
        return items.stream()
            .sorted(Comparator.comparing(Item::getPrice).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> filterConsumableItems(){
        return items.stream()
            .filter(it -> it.getType().equals(ItemType.CONSUMABLE))
            .collect(Collectors.toList());
    }

    @Override
    public List<Item> filterPowerupItems(){
        return items.stream()
            .filter(it -> it.getType() == ItemType.POWERUP)
            .collect(Collectors.toList());
    }
}

