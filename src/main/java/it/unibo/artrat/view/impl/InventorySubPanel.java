package it.unibo.artrat.view.impl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import it.unibo.artrat.controller.api.subcontroller.InventorySubController;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.view.api.InventoryView;

/** 
 * A base view for inventory.
 * @author Cristian Di Donato
*/
public class InventorySubPanel extends AbstractSubPanel implements InventoryView {

    private final InventorySubController controller;
    private final JPanel myJPanel = new JPanel();

    public InventorySubPanel(final InventorySubController controller) {
        this.controller = controller;
    }

    @Override
    protected void forceRedraw() {
        fillWithItems();
        myJPanel.revalidate();
        myJPanel.repaint();
    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(myJPanel, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void displayMessage(final String message, final String title) {
        JOptionPane.showMessageDialog(myJPanel, message, title, JOptionPane.INFORMATION_MESSAGE);
        myJPanel.revalidate();
        myJPanel.repaint();
    }

    private void fillWithItems() {
        myJPanel.removeAll();

        for (final var item : controller.getStoredItem()) { //observer.getStoredItem() {
            final JPanel itemPanel = new JPanel(new GridLayout(1, 2, 5, 0)); // Due colonne: itemButton e useButton

            final JButton itemButton = new JButton(controller.getTypeName(item));
            final JButton useButton = new JButton("Usa");

            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    controller.obtainDescription(item);
                }
            });

            useButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    if(confirmDialog("Vuoi far si che LuPino utilizzi questo oggetto?", "Utilizza oggetto")) {
                        if (controller.useItem(item)) { //observer.useItem()
                            myJPanel.remove(itemPanel);
                            forceRedraw();
                        }
                    }
                }
            });

            itemPanel.add(itemButton);
            itemPanel.add(useButton);
            myJPanel.add(itemPanel);

            /*final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            final JButton closeButton = new JButton("Chiudi inventario");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(confirmDialog("Vuoi davvero chiudere la borsa di LuPino e proseguire le tue scorribande?", "Chiudi inventario")) {
                        controller.quit();
                    }
                }
            });

            bottomPanel.add(closeButton);

            //Creazione dello scroll panel scalabile
            final JScrollPane scrollPane = new JScrollPane(myJPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Velocità di scrolling*/
        }

        final JButton btn = new JButton();
        btn.addActionListener((e) -> {
            this.controller.setStage(Stage.MENU);
        });
        myJPanel.add(btn);
    }

    @Override
    public void initComponents() {
        myJPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Una colonna, spazio verticale 5px
        fillWithItems();
        setPanel(myJPanel);
    }
}