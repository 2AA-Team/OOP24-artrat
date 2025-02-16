package it.unibo.artrat.view.impl;

import java.awt.*;
import javax.swing.*;

import it.unibo.artrat.controller.api.TimerController;
import it.unibo.artrat.controller.api.subcontroller.StoreSubController;
import it.unibo.artrat.model.api.inventory.ItemType;
import it.unibo.artrat.model.impl.Stage;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;
import it.unibo.artrat.view.api.MarketView;

/** 
 *  Market View, here you can buy items (powerup and consumable), and sort them in base of price, level and type.  
 *  @author Manuel Benagli
*/
public class MarketSubPanel extends AbstractSubPanel implements MarketView{   
    private final StoreSubController contr;
    //private final TimerController timerController;
    private final JPanel marketPanel = new JPanel();
    private final JPanel contPane = new JPanel(new BorderLayout());
    private final JScrollPane scrollPanel = new JScrollPane(marketPanel);
    private final ItemReader itemReader = new ItemReaderImpl();

    public MarketSubPanel(StoreSubController contr/* , TimerController timerController*/){    
        this.contr = contr;
        //this.timerController = timerController;         //timer controller
    }

    private boolean toConfirm(final String text, final String name){
        return JOptionPane.showConfirmDialog(marketPanel, text, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public void showMessage(final String message, final String name){
        JOptionPane.showMessageDialog(marketPanel, message, name, JOptionPane.INFORMATION_MESSAGE);
        marketPanel.revalidate();
        marketPanel.repaint();
    }
    
    @Override
    public void initComponents() {
        marketPanel.setLayout(new BorderLayout(8,8));
        scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contPane.add(scrollPanel, BorderLayout.CENTER);
        setShop();
        allItemsSetup();
        setPanel(contPane);
    }

    @Override
    protected void forceRedraw(){
        marketPanel.revalidate();
        marketPanel.repaint();
    }

    private void setShop(){
        final JButton filterButton = new JButton("Filter");
        final JButton sortButton = new JButton("Sort");
        final JButton searchItemButton = new JButton("Search");     //serve l'apposito per fare la search  
        final JPanel bottomPan = new JPanel();
        bottomPan.setLayout(new FlowLayout());
        final JPanel upperJPanel = new JPanel();
        upperJPanel.setLayout(new FlowLayout()); //o gridlayout
        final JButton playAgain = new JButton("Play");

        upperJPanel.add(filterButton);
        upperJPanel.add(sortButton);
        upperJPanel.add(searchItemButton);
        
        marketPanel.add(upperJPanel, BorderLayout.NORTH);

        filterButton.addActionListener(e ->{
            /*
            if(toConfirm("Do you want to filter the item in base of their price?", "Filter")){
                int choice = JOptionPane.showConfirmDialog(null,"creasing?", "Ordinamento Prezzi",  
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                
                contr.filterCategory(ItemType );
            }
            */
        });

        sortButton.addActionListener(e -> {
            if(toConfirm("Do you want to sort the item in base of their price?", "Sorting")){
                int choice = JOptionPane.showConfirmDialog(null,"creasing sorting = YES, decreasing = NO", "Ordinamento Prezzi", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                contr.sorting(choice);
            }
        });

        searchItemButton.addActionListener(e -> {
            //contr.searchItem();
        });

        playAgain.addActionListener(e->{
            if(toConfirm("Do you want to play a new game?", "Nuova partita")){
                contr.setStage(Stage.GAME);
              //  timerController.startTimer();                            //avvio il timer
            }
        });

        bottomPan.add(playAgain);
        marketPanel.add(bottomPan, BorderLayout.SOUTH);
    }

    private void allItemsSetup(){
        for(var purchItem : contr.purchasableItems()){
            final JPanel purchItemPanel = new JPanel(new GridLayout(1,4,5,0));      //da capire se usare un flowLay o grid
            final JButton buyItem = new JButton("Buy");
            
            String description = itemReader.getDescription(purchItem.toString()); 
            double price = itemReader.getPrice(purchItem.toString());
            String type = itemReader.getItemType(purchItem.toString()).toString();

            final JButton itemButton = new JButton("item");       //DEVO USARE LA FACTORY DI DIDO(perchè lì ho le classi di oggetti) E L'ITEM READER PER LEGGERE 
            final JButton priceButton = new JButton("$$");   
            final JButton typeButton = new JButton(type);    

            purchItemPanel.add(itemButton);
            purchItemPanel.add(typeButton);
            purchItemPanel.add(priceButton);
            purchItemPanel.add(buyItem);

            priceButton.addActionListener(e->{
                contr.getItemPrice(purchItem);
            });

            typeButton.addActionListener(e->{
                contr.getTypeName(purchItem);
            });

            itemButton.addActionListener(e->{
                contr.getDescription(purchItem);
            });

            buyItem.addActionListener(e ->{
                if(toConfirm("Vuoi davvero acquistare?", "Compra")){
                    if(contr.buyItem(purchItem) && purchItem.getType().equals(ItemType.POWERUP)){
                        marketPanel.remove(purchItemPanel);     //se è un powerup, dato che è una passiva, lo rimuovo dallo shop
                        forceRedraw();
                    }
                }
            });

            marketPanel.add(purchItemPanel, BorderLayout.CENTER);
        }
    }
}
