package it.unibo.artrat.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import it.unibo.artrat.model.api.characters.Inventory;

/** 
 * A base view for inventory
 * @author Cristian Di Donato
*/
public class InventorySubPanel extends AbstractSubPanel{

    @Override
    public void initComponents() {
        /*
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 700);
    
        // Creazione del pannello principale
        myJPanel = new JPanel();
        myJPanel.setLayout(new BoxLayout(myJPanel, BoxLayout.Y_AXIS)); // Disposizione verticale dei pannelli
    
        // Aggiunta di un pannello per ogni item dell'inventario
        for (var item : inv.getStoredItem()) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
    
            JButton itemButton = new JButton(item.getType().name());
            itemButton.setPreferredSize(new Dimension(300, (myJPanel.getHeight()/5))); // questo riadatta l'altezza quindi rende tutto "scalabile" 
            //itemButton.setPreferredSize(new Dimension(300, 100)); Questa versione mantiene l'altezza fissa ed è congeniale all'approccio con lo scroll panel
    
            JButton useButton = new JButton("Usa");
            useButton.setPreferredSize(new Dimension(150, (myJPanel.getHeight()/5))); // Stesso discorso dell'item button
            //useButton.setPreferredSize(new Dimension(150, 100)); Stesso discorso dell'item button

            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int result = JOptionPane.showConfirmDialog(
                        InventoryViewImpl.this,
                        "Vuoi far si che LuPino utilizzi questo oggetto?",
                        "Utilizza oggetto",
                        JOptionPane.YES_NO_OPTION
                    );
    
                    if (result == JOptionPane.YES_OPTION) {
                        if(inv.useItem(item)){
                            myJPanel.remove(itemPanel);
                            myJPanel.revalidate();
                            myJPanel.repaint();
                            JOptionPane.showMessageDialog(
                                InventoryViewImpl.this,
                                "L'oggetto è stato utilizzato da LuPino con successo e perciò rimosso dall'inventario",
                                "Uno in meno bello",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                }
            });
    
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(
                        InventoryViewImpl.this,
                        item.getDescription(),
                        "Descrizione",
                        JOptionPane.CLOSED_OPTION
                    );
                }
            });
    
            itemPanel.add(itemButton, BorderLayout.WEST);
            itemPanel.add(useButton, BorderLayout.CENTER);
    
            myJPanel.add(itemPanel);
        }
    
        // Aggiunta di un pulsante per chiudere la finestra
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Chiudi inventario");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                    InventoryViewImpl.this,
                    "Vuoi davvero chiudere la borsa di LuPino e proseguire le tue scorribande?",
                    "Chiudi inventario",
                    JOptionPane.YES_NO_OPTION
                );
    
                if (result == JOptionPane.YES_OPTION) {
                    //InventoryViewImpl.this.setVisible(false); Non chiude la finestra ma bensì la nasconde bisogna decidere che approccio usare
                    //dispose(); chiude la finestra corrente senza influenzare quelel sotto, potrebbe essere utile nella versione finale
                    System.exit(0);
                }
            }
        });
        bottomPanel.add(closeButton);
    
        // Il pannello di scroll permette di scorrere in verticale per vedere i vari pulsanti in caso di dimensioni troppo piccole
        //JScrollPane scrollPane = new JScrollPane(myJPanel);
        //this.add(scrollPane, BorderLayout.CENTER);
        this.add(myJPanel, BorderLayout.CENTER); //Version con altezza scalabile
        this.add(bottomPanel, BorderLayout.SOUTH);
    
        this.setVisible(true);
        */
    }
    
}
