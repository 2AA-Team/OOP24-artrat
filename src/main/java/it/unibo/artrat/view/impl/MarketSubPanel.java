package it.unibo.artrat.view.impl;

import java.awt.*;
import javax.swing.*;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.model.impl.WorldTimerImpl;
import it.unibo.artrat.view.api.MarketView;

public class MarketSubPanel extends AbstractSubPanel implements MarketView {
    private static final int GAP = 7;
    private StoreSubController contr;
    private final JPanel marketPanel = new JPanel();
    private final JPanel contPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(marketPanel);
    private final JLabel lupinoCash = new JLabel();
    private JPanel purchItemPanel;
    private WorldTimerImpl timer;
    
    public MarketSubPanel(StoreSubController contr) {
        this.contr = contr;
        this.timer = new WorldTimerImpl(this.contr);
        this.purchItemPanel = new JPanel(new GridLayout(contr.purchasableItems().size(), 4, 4, 2));
        this.timer.startTimer();
    }

    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(marketPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void showMessage(final String message, final String name) {
        JOptionPane.showMessageDialog(marketPanel, message, name, JOptionPane.INFORMATION_MESSAGE);
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    @Override
    public void initComponents() {
        marketPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contPane.add(scrollPanel, BorderLayout.CENTER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setShop();
        allItemsSetup();
        updateCoinLabel();
        setPanel(contPane);
    }

    @Override
    protected void forceRedraw() {
        allItemsSetup();
        marketPanel.revalidate();
        marketPanel.repaint();
    
    }

    private void updateCoinLabel() {
        lupinoCash.setText(String.valueOf(contr.getModel().getPlayer().getCoin().getCurrentAmount()));
    }

    private void setShop() {
        final JButton filterButton = new JButton("Filter");
        final JButton sortButton = new JButton("Sort");
        final JButton searchItemButton = new JButton("Search");
        final JPanel bottomPan = new JPanel();
        bottomPan.setLayout(new FlowLayout());
        final JPanel upperJPanel = new JPanel();
        upperJPanel.setLayout(new FlowLayout());
        final JButton toMenu = new JButton("BACK");

        upperJPanel.add(filterButton);
        upperJPanel.add(sortButton);
        upperJPanel.add(searchItemButton);

        marketPanel.add(upperJPanel, BorderLayout.NORTH);

        filterButton.addActionListener(e -> {
            if (toConfirm("Do you want to filter the item in base of their type?", "Filter")) {
                int choice = JOptionPane.showConfirmDialog(null, "POWEUP filter?", "Filter type",  
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // contr.filterCategory(ItemType);
            }
        });

        sortButton.addActionListener(e -> {
            if (toConfirm("Do you want to sort the item in base of their price?", "Sorting")) {
                int choice = JOptionPane.showConfirmDialog(null, "creasing sorting = NO, decreasing = YES", "Ordinamento Prezzi", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                contr.sorting(choice);
                forceRedraw();
            }
        });

        searchItemButton.addActionListener(e -> {
            // contr.searchItem();
        });

        toMenu.addActionListener(e -> {
            if (toConfirm("Do you want to come back to the menu?", "Back to menu")) {
                contr.setStage(Stage.MENU);
            }
        });

        bottomPan.add(toMenu);
        bottomPan.add(lupinoCash);
        marketPanel.add(bottomPan, BorderLayout.SOUTH);
    }

    private void allItemsSetup() {
        purchItemPanel.removeAll();  
        System.out.println("VIEEEEEWWWW" + contr.purchasableItems());
        for (var purchItem : contr.purchasableItems()) {
            final JButton buyItem = new JButton("Buy");
            final JLabel itemLabel = new JLabel(contr.getItemName(purchItem));
            final JLabel typeLabel = new JLabel(contr.getTypeName(purchItem));
            final JLabel priceButton = new JLabel(contr.getItemPrice(purchItem) + "$");
             
            JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemPanel.add(itemLabel);
            itemPanel.add(typeLabel);
            itemPanel.add(priceButton);
            itemPanel.add(buyItem);

            purchItemPanel.add(itemPanel);

            buyItem.addActionListener(e -> {
                if (toConfirm("Vuoi davvero acquistare?", "Compra")) {
                    if (contr.buyItem(purchItem)) {
                        if (purchItem.getType().equals(ItemType.POWERUP)) {
                            contr.getModel().getMarket().getPurchItems().remove(purchItem);
                            purchItemPanel.remove(itemPanel);  
                        }
                        forceRedraw();
                        updateCoinLabel();
                    }
                }
            });
        }
        marketPanel.add(purchItemPanel, BorderLayout.CENTER); 
    }
}