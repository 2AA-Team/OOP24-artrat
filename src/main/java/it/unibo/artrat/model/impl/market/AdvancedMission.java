package it.unibo.artrat.model.impl.market;


/**
 *  AdvancedMission             FARLO COME UN DECORATORRRRRRRRRRRRRRRRRRRRR 
 */

public class AdvancedMission extends AbstractMissionCreator{

    private final MissionCategory category;                //La categoria è utile se devo filtrare le missioni
    private final int diffic; 

    //posso fare che l'advanced ha anche categorie di effetti benefici
    //

    public AdvancedMission(String name, String descr, Double reward, MissionCategory category, int difficulty){
        super(name, descr, reward);
        this.diffic = difficulty;    //LA DIFFICOLTA' SARA' UN MOLTIPLICATORE DEL PUNTEGGIO
        this.category = category;
    }

    @Override
    public boolean isDone(){
        return false;
    }

    /*
    @Override
    public MissionCategory getCategory(){
        return this.category;
    }
    */

    /**
     * 
     * @return an integer number, which will be a score multiplier
     */
    @Override
    public int getDifficulty(){     //IL PROBLEMA E' CHE LA DIFFICOLTA' NON E' NELLE BASE MISSIONS,
        return this.diffic;         // FORSE MI SERVE UNA INTERFACCIA O UNA ABSTRACT PER LE ADVANCED
    }


}
