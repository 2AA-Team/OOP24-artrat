package it.unibo.artrat.view.impl;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 *  Market View, here you can buy items (powerup and consumable), and sort them in base of price, level and type.  
 * 
*/
public class MarketViewImpl extends JFrame{
    private final JPanel marketPanel = new JPanel();
    private final JButton playAgain = new JButton("Play");
    private final JButton showMission = new JButton("M");
    
    //per il filter e il sort posso anche usare un menù a tendina, OPPURE GROUP LAYOUT
    //SONO BOTTONI PROVVISO
    private final JButton filterButton = new JButton("Filter");
    private final JButton sortButton = new JButton("Sort");
    private final JButton searchItemButton = new JButton();     //serve l'apposito per fare la search

    //private final JButton saveButton = new JButton("save game");    //per salvare, ancora opzionale
    
    public MarketViewImpl(){        
        this.setTitle("State of Art Market");
        this.setSize(900,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 

        /*
        * metto un BorderLayout per impostare in alto i bottoni per ItemManager, 
        * in basso per il newGame, Missioni e punteggio player, al centro per comprare
        */
        marketPanel.setLayout(new BorderLayout());     

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
