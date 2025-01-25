package it.unibo.artrat.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.model.api.characters.Inventory;

/** 
 * A base view for inventory
 * @author Cristian Di Donato
*/
public class InventorySubPanel extends AbstractSubPanel {

    private final InventorySubController controller;

    public InventorySubPanel(final InventorySubController controller) {
        this.controller = controller;
    }

    @Override
    public void initComponents() {
    
        JPanel myJPanel = new JPanel();
        myJPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Una colonna, spazio verticale 5px

        // Aggiunta di un pannello per ogni item dell'inventario
        for (var item : controller.getStoredItem()) { //observer.getStoredItem() {
            JPanel itemPanel = new JPanel(new GridLayout(1, 2, 5, 0)); // Due colonne: itemButton e useButton

            JButton itemButton = new JButton(controller.getTypeName(item));
            JButton useButton = new JButton("Usa");

            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.getDescription(item);
                }
            });

            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*if(confirmDialog("Vuoi far si che LuPino utilizzi questo oggetto?", "Utilizza oggetto")) {
                        if (controller.useItem(item)) { //observer.useItem()
                            myJPanel.remove(itemPanel);
                        }
                    }*/
                }
            });

            /*this.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    Dimension newSize = getSize();
                    int buttonWidth = newSize.width / 5;
                    int buttonHeight = newSize.height / 10;

                    itemButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
                    useButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

                    itemPanel.revalidate();
                    itemPanel.repaint();
                }
            });*/

            itemPanel.add(itemButton);
            itemPanel.add(useButton);
            myJPanel.add(itemPanel);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton closeButton = new JButton("Chiudi inventario");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    /*if(confirmDialog("Vuoi davvero chiudere la borsa di LuPino e proseguire le tue scorribande?", "Chiudi inventario")) {
                        controller.quit();
                    }*/
                }
            });

            bottomPanel.add(closeButton);

            // Creazione dello scroll panel scalabile
            JScrollPane scrollPane = new JScrollPane(myJPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocità di scrolling
        };
    
    }

    @Override
    protected void forceRedraw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'forceRedraw'");
    }

    /*private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(this, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        myJPanel.revalidate();
        myJPanel.repaint();
    }

    @Override
    public void closeWindow() {
        this.dispose();
    }*/
    
}
