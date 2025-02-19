package it.unibo.artrat.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.model.impl.WorldTimerImpl;
//import it.unibo.artrat.model.impl.WorldTimerImpl;
import it.unibo.artrat.view.api.MarketView;

/**
 * Here I see the collection of all the items I can purchase, and different buttons, 
 * whose operation is dependent on each other.
 * @author Manuel Benagli
 */
public class MarketSubPanel extends AbstractSubPanel implements MarketView {
    private static final ItemType ITEMTYPE_ALL = null;
    private static final int SEARCH_TEXT_FIELD = 20;
    private static final int GAP = 7;
    private final StoreSubController contr;
    private final JPanel marketPanel = new JPanel();
    private final JPanel contPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(marketPanel);
    private final JLabel lupinoCash = new JLabel();
    private JPanel purchItemPanel = new JPanel();
    private final JTextField searchItemField = new JTextField(SEARCH_TEXT_FIELD); 
    private final WorldTimerImpl timer = new WorldTimerImpl(); 
    /**
     * MarketSubPanel constructor.
     * @param contr
     */
    public MarketSubPanel(final StoreSubController contr) {
        this.contr = contr;
    }

    /**
     * 
     * @param text message test
     * @param name name of test
     * @return s.
     */
    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(marketPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * 
     */
    @Override
    public void showMessage(final String message, final String name) {
        JOptionPane.showMessageDialog(marketPanel, message, name, JOptionPane.INFORMATION_MESSAGE);
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    /**
     * 
     */
    @Override
    public void initComponents() {
        marketPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contPane.add(scrollPanel, BorderLayout.CENTER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        contr.initItemList();
        this.purchItemPanel = new JPanel(new GridLayout(contr.purchasableItems().size(), 4, 4, 2));
        setShop();
        allItemsSetup();
        updateCoinLabel();
        setPanel(contPane);
    }

    /**
     * 
     */
    @Override
    protected void forceRedraw() {
        allItemsSetup();
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    /**
     * this private method updates the coin label every time I buy a new item.
     */
    private void updateCoinLabel() {
        lupinoCash.setText(String.valueOf(contr.getModel().getPlayer().getCoin().getCurrentAmount()));
    }

    /**
     * 
     */
    private void setShop() {
        final JButton sortButton = new JButton("Sort");
        final JPanel bottomPan = new JPanel();
        bottomPan.setLayout(new FlowLayout());
        final JPanel upperJPanel = new JPanel();
        upperJPanel.setLayout(new FlowLayout());
        final JButton toMenu = new JButton("BACK");


        final JButton timerButton = new JButton("TIMER");
        final JButton stoptimerButton = new JButton("STOP");
        

        final JComboBox<ItemType> filterComboBox = new JComboBox<>();
        filterComboBox.addItem(ITEMTYPE_ALL);

        for (final ItemType type : ItemType.values()) {
            filterComboBox.addItem(type);
        }

        upperJPanel.add(filterComboBox);
        upperJPanel.add(sortButton);
        upperJPanel.add(searchItemField);
        marketPanel.add(upperJPanel, BorderLayout.NORTH);

        filterComboBox.addActionListener(e -> {
            final ItemType selectedType = (ItemType) filterComboBox.getSelectedItem();
            contr.filterCategory(selectedType);
            forceRedraw();
        });

        sortButton.addActionListener(e -> {
            final int choice = JOptionPane.showConfirmDialog(null, "creasing sorting = NO, decreasing = YES", 
            "Ordinamento Prezzi", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            contr.sorting(choice);
            forceRedraw();
        });

        /*
         * I used 
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

        stoptimerButton.addActionListener(e->{
            timer.stopTimer();
        });

        timerButton.addActionListener(e->{
            timer.startTimer();
        });

        toMenu.addActionListener(e -> {
            if (toConfirm("Do you want to come back to the menu?", "Back to menu")) {
                searchItemField.setText("");
                contr.filterCategory(ITEMTYPE_ALL);
                itemSearch(""); 
                contr.setStage(Stage.MENU);
            }
        });


        upperJPanel.add(stoptimerButton);
        upperJPanel.add(timerButton);


        bottomPan.add(toMenu);
        bottomPan.add(lupinoCash);
        marketPanel.add(bottomPan, BorderLayout.SOUTH);
    }

    /**
     * 
     * @param searchText s.
     */
    private void itemSearch(final String searchText) {
        contr.searchItem(searchText);
        forceRedraw();
    }

    /**
     * 
     */
    private void allItemsSetup() {
        purchItemPanel.removeAll();

        for (final var purchItem : contr.purchasableItems()) {
            final JButton buyItem = new JButton("Buy");
            final JLabel itemLabel = new JLabel(contr.getItemName(purchItem));
            final JLabel typeLabel = new JLabel(contr.getTypeName(purchItem));
            final JLabel priceButton = new JLabel(contr.getItemPrice(purchItem) + "$");
            final JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            itemPanel.add(itemLabel);
            itemPanel.add(typeLabel);
            itemPanel.add(priceButton);
            itemPanel.add(buyItem);

            purchItemPanel.add(itemPanel);

            buyItem.addActionListener(e -> {
                if (toConfirm("Vuoi davvero acquistare?", "Compra") && contr.buyItem(purchItem)) {
                    if (purchItem.getType().equals(ItemType.POWERUP)) {
                        contr.getModel().getMarket().getPurchItems().remove(purchItem);
                        purchItemPanel.remove(itemPanel);
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
