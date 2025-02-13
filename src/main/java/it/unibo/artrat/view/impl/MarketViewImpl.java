package it.unibo.artrat.view.impl;

import java.awt.*;
import javax.swing.*;

import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.impl.Stage;

/** 
 *  Market View, here you can buy items (powerup and consumable), and sort them in base of price, level and type.  
 *  @author Manuel Benagli
*/
public class MarketViewImpl extends JFrame{   
    private final StoreSubController contr;
    private final JPanel marketPanel = new JPanel();

    public MarketViewImpl(StoreSubController contr){    
        this.contr = contr;
    }

    private boolean toConfirm(final String text, final String name){
        return JOptionPane.showConfirmDialog(marketPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    public void initComponents(){
        //per il filter e il sort posso anche usare un menù a tendina, OPPURE GROUP LAYOUT
        //SONO BOTTONI PROVVISORI
        final JButton filterButton = new JButton("Filter");
        final JButton sortButton = new JButton("Sort");
        final JButton searchItemButton = new JButton();     //serve l'apposito per fare la search
        final JButton showMission = new JButton("M");

        marketPanel.setLayout(new BorderLayout(7,7));     

        for(var purchItem : contr.purchasableItems()){
            final JPanel purchItemPanel = new JPanel(new GridLayout(1,2,5,1));      //da capire se usare un flowLay o grid
            final JButton buyItem = new JButton("Buy");
            final JButton itemButton = new JButton();       //DA CAPIRE COME LEGGERE OGNI ITEM (se da file ...)
            final JButton priceButton = new JButton();   
            final JButton typeButton = new JButton();    

            purchItemPanel.add(itemButton);
            purchItemPanel.add(buyItem);

            priceButton.addActionListener(e->{
                contr.getTypeName(purchItem);
            });

            itemButton.addActionListener(e->{
                contr.getDescription(purchItem);
                contr.getTypeName(purchItem);
            });

            buyItem.addActionListener(e ->{
                if(purchItem.getPrice() < purchItem.getPrice()){
                    
                }
            });
        }

        final JPanel bottomPan = new JPanel();
        bottomPan.setLayout(new FlowLayout());
        marketPanel.add(bottomPan, BorderLayout.SOUTH);

        final JPanel upperJPanel = new JPanel();
        upperJPanel.setLayout(new GridLayout());
        final JButton playAgain = new JButton("Play");

        playAgain.addActionListener(e->{
            if(toConfirm("Do you want to play a new game?", "Esci dallo shop")){
                contr.setStage(Stage.GAME);
            }
        });

        showMission.addActionListener(e->{  //da switchare panel, oppure semplicemente usare questo se trovo un modo, anche se è meglio separato
            if(toConfirm("Vuoi visualizzare le missioni?", "Apri le missioni")){
                //o cambio panel o no
            }
        });

        this.setVisible(true);
    }
}
