package it.unibo.artrat.utils.impl;

import java.util.Set;

import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.utils.api.ItemReader;

/**
 * An implementation of ItemReader interface.
 * @author Cristian Di Donato.
 */
public class ItemReaderImpl extends AbstractReader implements ItemReader {
    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(final String nameOfItem) {
        return super.getSpecificFiled(nameOfItem, "name");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(final String nameOfItem) {
        return super.getSpecificFiled(nameOfItem, "description");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getPrice(final String nameOfItem) {
        return Double.parseDouble(super.getSpecificFiled(nameOfItem, "price"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ItemType getItemType(final String nameOfItem) {
        return ItemType.valueOf(super.getSpecificFiled(nameOfItem, "itemType"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAllItemsName() {
        return super.getKeySetMap();
    }
}
