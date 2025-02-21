package it.unibo.artrat.view.impl;

import java.awt.*;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.MarketView;

/**
 * Here I see the collection of all the items I can purchase, and different buttons. 
 * The operations of the buttons are dependent on each other.
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
     * MarketSubPanel constructor.
     * @param contr
     */
    public MarketSubPanel(final StoreSubController contr) {
        this.contr = contr;
    }

    /**
     * @param text message test
     * @param name name of test
     * @return a confirm message when it's needed
     */
    private boolean toConfirm(final String text, final String name) {
        return JOptionPane.showConfirmDialog(marketPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * this method is called by toConfirm, it shows my message and update the view.
     */
    @Override
    public void showMessage(final String message, final String name) {
        JOptionPane.showMessageDialog(marketPanel, message, name, JOptionPane.INFORMATION_MESSAGE);
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    /**
     * initComponents method.
     */
    @Override
    public void initComponents() {
        marketPanel.setLayout(new BorderLayout(GAP, GAP));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contPane.add(scrollPanel, BorderLayout.CENTER);
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        contr.initItemList();

        // Cambiato il layout di purchItemPanel per farlo essere flessibile
        this.purchItemPanel = new JPanel(new GridLayout(0, 1, GAP, GAP)); // Righe dinamiche, una per ogni item
        setShop();
        allItemsSetup();
        updateCoinLabel();
        setPanel(contPane);
    }

     /**
     * This method forces a view update.
     * I call the method allItemsSetup to read from my item List, which can be
     * modified temporally (with filter, sort and search), and permanently (if I buy a powerup).
     */
    @Override
    protected void forceRedraw() {
        allItemsSetup();
        marketPanel.revalidate();
        marketPanel.repaint();
    }
    
    //this private method updates the coin label every time I buy a new item.
    private void updateCoinLabel() {
        lupinoCash.setText(String.valueOf(contr.getModel().getPlayer().getCoin().getCurrentAmount()));
    }

    private void setShop() {
        final JButton sortButton = new JButton("Sort");
        final JPanel bottomPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JPanel upperJPanel = new JPanel(new GridBagLayout());  // Usato GridBagLayout per una gestione flessibile
        final JButton toMenu = new JButton("BACK");

        final JComboBox<ItemType> filterComboBox = new JComboBox<>();
        filterComboBox.addItem(ITEMTYPE_ALL);

        for (final ItemType type : ItemType.values()) {
            filterComboBox.addItem(type);
        }

        // Impostato il layout di GridBagLayout per fare in modo che i componenti siano ridimensionabili
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Questo rende il componente espandibile orizzontalmente
        upperJPanel.add(filterComboBox, gbc);

        gbc.gridx = 1;
        upperJPanel.add(sortButton, gbc);

        gbc.gridx = 2;
        upperJPanel.add(searchItemField, gbc);

        marketPanel.add(upperJPanel, BorderLayout.NORTH);

        // Aggiungi il listener per il filtro
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

        searchItemField.getDocument().addDocumentListener(new DocumentListener() {
           
            // I call itemSearch method at every character inserted into the search field.
            @Override
            public void insertUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }

             // I call itemSearch method at every character removed into the search field.
            @Override
            public void removeUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }
            
            // I call itemSearch method at every changed update.
            @Override
            public void changedUpdate(final DocumentEvent doc) {
                itemSearch(searchItemField.getText().trim().toLowerCase(Locale.ROOT));
            }
        });

        // Aggiungi il listener per il pulsante "Back"
        toMenu.addActionListener(e -> {
            if (toConfirm("Do you want to come back to the menu?", "Back to menu")) {
                searchItemField.setText("");
                contr.filterCategory(ITEMTYPE_ALL);
                itemSearch(""); 
                contr.setStage(Stage.MENU);
            }
        });

        bottomPan.add(toMenu);
        bottomPan.add(lupinoCash);
        marketPanel.add(bottomPan, BorderLayout.SOUTH);
    }

    private void itemSearch(final String searchText) {
        contr.searchItem(searchText);
        forceRedraw();
    }

    /**
     * This method reads from my StoreSubControllerImpl a list of purchasableItems.
     * For every item I read, I create a nel panel, with three labels (item name, item type,
     * item price) and a button to buy it
     * The purchasableItems are read using ItemReaderImpl.
     * When I buy an item, if the item is a powerup, the item is cancelled in the market.
     */
    private void allItemsSetup() {
        purchItemPanel.removeAll();

        for (final var purchItem : contr.purchasableItems()) {
            final JButton buyItem = new JButton("Buy");
            final JLabel itemLabel = new JLabel(contr.getItemName(purchItem));
            final JLabel typeLabel = new JLabel(contr.getTypeName(purchItem));
            final JLabel priceButton = new JLabel(contr.getItemPrice(purchItem) + "$");

            // Layout per ogni singolo item: 3 label e un bottone su ogni riga
            final JPanel itemPanel = new JPanel(new GridLayout(1, 4, GAP, GAP));  // 1 riga, 4 colonne
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
