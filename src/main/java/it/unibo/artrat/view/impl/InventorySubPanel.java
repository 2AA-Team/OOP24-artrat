package it.unibo.artrat.view.impl;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import it.unibo.artrat.model.api.characters.Inventory;

/** 
 * A base view for inventory
 * @author Cristian Di Donato
*/
public class InventorySubPanel extends AbstractSubPanel{

    @Override
    public void initComponents() {
        /** 
        panel.setLayout(new GridLayout(0, 2, 10, 10)); // Disposizione griglia con 2 colonne

        // Aggiunta di un pulsante per ogni item dell'inventario
        for (var item : inv.getStoredItem()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());

                JButton itemButton = new JButton(item.getType().name());
                itemButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(InventoryViewImpl.this, item.getDescription());
                    }
                });
    
                itemPanel.add(itemButton, BorderLayout.CENTER);

            panel.add(itemPanel);
        }

        // Aggiunta del pannello alla finestra
        this.add(panel);
        this.setVisible(true);
        */
    }
    
}
