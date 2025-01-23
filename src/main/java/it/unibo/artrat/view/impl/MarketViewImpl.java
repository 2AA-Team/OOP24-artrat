package it.unibo.artrat.view.impl;

import javax.swing.*;

/** 
 *  Market View, here you can buy items (powerup and consumable), and sort them in base of price, level and type
 *  
*/
public class MarketViewImpl extends JFrame{

    /*
     * in alto, pulsanti di filtraggio, sorting e ricerca
     */
    private final JPanel marketPanel = new JPanel();
    private final JButton playAgain = new JButton("Play");
    private final JButton showMission = new JButton("M");
    
    //per il filter e il sort posso anche usare un menù a tendina
    private final JButton filterButton = new JButton("Filter");
    private final JButton sortButton = new JButton("Sort");
    private final JButton searchItemButton = new JButton();     //serve l'apposito per fare la search

    private final JButton saveButton = new JButton("save game");    //per salvare


    public MarketViewImpl(){
        this.setTitle("State of Art Market");
        this.setSize(900,900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); 

        marketPanel.setLayout(new BorderLayout());
        final JPanel bottomPan = new JPanel();
        
        bottomPan.setLayout(new BorderLayout());
        marketPanel.add(playAgain);




        playAgain.addActionListener(e->{
            
        });
    }
}
