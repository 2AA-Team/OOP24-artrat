package it.unibo.artrat.view.impl;

import javax.swing.*;

import it.unibo.artrat.model.api.characters.Inventory;
import java.awt.*;
import java.awt.event.*;

public class InventoryViewImpl extends JFrame {
    private JPanel myJPanel;

    public InventoryViewImpl(Inventory inv) {
        super("Invetory Player");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450,700);

        // Creazione del pannello principale
        myJPanel = new JPanel();
        myJPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Disposizione griglia con 2 colonne

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

            myJPanel.add(itemPanel);
        }

        // Aggiunta del pannello alla finestra
        this.add(myJPanel);
        this.setVisible(true);

    }

}
