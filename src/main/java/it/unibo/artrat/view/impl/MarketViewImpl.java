package it.unibo.artrat.view.impl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;

/** 
 *  Market View, here you can buy items (powerup and consumable), and sort them in base of price, level and type.  
 * 
*/
public class MarketViewImpl extends JFrame{   
    private final StoreSubController contr;

    public MarketViewImpl(StoreSubController contr){    
        this.contr = contr;
    }

    public void initComponents(){
        final JPanel marketPanel = new JPanel();
        
        //per il filter e il sort posso anche usare un menù a tendina, OPPURE GROUP LAYOUT
        //SONO BOTTONI PROVVISORI
        final JButton filterButton = new JButton("Filter");
        final JButton sortButton = new JButton("Sort");
        final JButton searchItemButton = new JButton();     //serve l'apposito per fare la search
        final JButton playAgain = new JButton("Play");
        final JButton showMission = new JButton("M");

        marketPanel.setLayout(new BorderLayout());     

        for(var purchItem : contr.purchasableItems()){
            
        }

        final JPanel bottomPan = new JPanel();
        bottomPan.setLayout(new FlowLayout());
        marketPanel.add(bottomPan, BorderLayout.SOUTH);

        final JPanel upperJPanel = new JPanel();
        upperJPanel.setLayout(new GridLayout());    //da vedere meglio

        playAgain.addActionListener(e->{
            //da switchare panel, nuova partita
        });

        showMission.addActionListener(e->{
            //da switchare panel, oppure semplicemente usare questo se trovo un modo, anche se è meglio separato            
        });

        this.setVisible(true);
    }
}
