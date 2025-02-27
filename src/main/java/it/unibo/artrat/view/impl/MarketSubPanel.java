package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.view.api.MarketView;

/**
 * Here I see the collection of all the items I can purchase, and different
 * buttons (sort, filter and a search wield).
 * Sort, filter and search are dependent on each other.
 * 
 * @author Manuel Benagli
 */
public class MarketSubPanel extends AbstractSubPanel implements MarketView {
    private static final ItemType ITEMTYPE_ALL = null;
    private static final int SEARCH_TEXT_FIELD = 12;
    private static final int GAP = 5;
    private final StoreSubController contr;
    private final JPanel marketPanel = new JPanel();
    private final JPanel contPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(marketPanel);
    private final JLabel lupinoCash = new JLabel();
    private JPanel purchItemPanel = new JPanel();
    private final JTextField searchItemField = new JTextField(SEARCH_TEXT_FIELD);

    /**
     * ShopSubPanel constructor.
     * 
     * @param contr ShopSubController.
     */
    public MarketSubPanel(final StoreSubController contr) {
        this.contr = contr;
    }

    /*
     * return a confirm message when it's needed.
     */
    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(marketPanel, text, name,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMessage(final String message, final String name) {
        JOptionPane.showMessageDialog(marketPanel, message, name, JOptionPane.INFORMATION_MESSAGE);
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initComponents() {
        marketPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contPane.add(scrollPanel, BorderLayout.CENTER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        contr.initItemList();
        this.purchItemPanel = new JPanel(new GridLayout(0, 1, GAP, GAP));
        setShop();
        allItemsSetup();
        updateCoinLabel();
        setPanel(contPane);
    }

    /**
     * I call the method allItemsSetup to read from my item List, which can be
     * modified temporally (with filter, sort and search), and permanently (if I buy
     * a powerup).
     */
    @Override
    public void forceRedraw() {
        allItemsSetup();
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    private void updateCoinLabel() {
        lupinoCash.setText("COINS: " + contr.getCurrentAmount() + " $");
    }

    private void setShop() {
        final JButton sortButton = new JButton("Sort");
        final JPanel bottomPan = new JPanel(new BorderLayout());
        final JPanel upperJPanel = new JPanel(new GridBagLayout());
        final JButton toMenu = new JButton("BACK");

        final JComboBox<ItemType> filterComboBox = new JComboBox<>();
        filterComboBox.addItem(ITEMTYPE_ALL);

        for (final ItemType type : ItemType.values()) {
            filterComboBox.addItem(type);
        }

        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        upperJPanel.add(filterComboBox, gbc);

        gbc.gridx = 1;
        upperJPanel.add(sortButton, gbc);
        gbc.gridx = 2;
        upperJPanel.add(searchItemField, gbc);

        marketPanel.add(upperJPanel, BorderLayout.NORTH);

        filterComboBox.addActionListener(e -> {
            final ItemType selectedType = (ItemType) filterComboBox.getSelectedItem();
            contr.filterCategory(selectedType);
            forceRedraw();
        });

        /*
         * If I exit from YesNoOption, there will be a creasing sorting.
         * That's because we already clicked for sorting, and usually people sorts from
         * lowest.
         */
        sortButton.addActionListener(e -> {
            final int choice = JOptionPane.showOptionDialog(
                    null,
                    "Choose your sorting preference:",
                    "Price Sorting",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    List.of("Decreasing", "Increasing").toArray(),
                    "Increasing");
            contr.sorting(choice);
            forceRedraw();
        });

        /*
         * I call itemSearch method at every character inserted or removed into the
         * search field.
         * itemSearch is also called with changedUpdate method, at every changed update
         * in the view.
         */
        searchItemField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }

            @Override
            public void removeUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }

            @Override
            public void changedUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }
        });

        toMenu.addActionListener(e -> {
            exitSettings();
            contr.goToMenu();
        });

        bottomPan.add(toMenu, BorderLayout.EAST);
        bottomPan.add(lupinoCash, BorderLayout.WEST);
        marketPanel.add(bottomPan, BorderLayout.SOUTH);
    }

    private void itemSearch(final String searchText) {
        contr.searchItem(searchText);
        forceRedraw();
    }

    private void exitSettings() {
        searchItemField.setText("");
        contr.filterCategory(ITEMTYPE_ALL);
        itemSearch("");
    }

    /**
     * This method reads a list of purchasableItems, read using ItemReaderImpl.
     * For every item I read, I create a nel panel, with three labels (item name,
     * item type, item price) and a button to buy it.
     * When I buy an item, if the item is a powerup, the item is cancelled in the
     * shop.
     */
    private void allItemsSetup() {
        purchItemPanel.removeAll();

        for (final var purchItem : contr.purchasableItems()) {
            final JButton buyItem = new JButton("Buy");
            final JLabel itemLabel = new JLabel(contr.getItemName(purchItem));
            final JLabel typeLabel = new JLabel(contr.getItemType(purchItem).toString());
            final JLabel priceButton = new JLabel(contr.getItemPrice(purchItem) + "$");
            final JPanel itemPanel = new JPanel(new GridLayout(1, 4, GAP, GAP));
            itemPanel.add(itemLabel);
            itemPanel.add(typeLabel);
            itemPanel.add(priceButton);
            itemPanel.add(buyItem);

            purchItemPanel.add(itemPanel);

            buyItem.addActionListener(e -> {
                if (toConfirm("Do you really want to buy?", "Buy")) {
                    if (contr.getCurrentAmount() >= contr.getItemPrice(purchItem)) {
                        if (contr.getItemType(purchItem).equals(ItemType.POWERUP)) {
                            purchItemPanel.remove(itemPanel);
                        }
                        contr.buyItem(purchItem);
                    } else {
                        showMessage("Not enough money", "Purchase denied");
                    }
                    itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
                    forceRedraw();
                    updateCoinLabel();
                }
            });
        }
        marketPanel.add(purchItemPanel, BorderLayout.CENTER);
    }
}
