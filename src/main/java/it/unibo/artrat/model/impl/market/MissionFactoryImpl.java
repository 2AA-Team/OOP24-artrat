package it.unibo.artrat.model.impl.market;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import it.unibo.artrat.model.api.market.Mission;
import it.unibo.artrat.model.api.market.MissionFactory;
import it.unibo.artrat.utils.api.ItemReader;
import it.unibo.artrat.utils.impl.ItemReaderImpl;

/**
 * 
 */
public class MissionFactoryImpl implements MissionFactory {

    private final URL itemPath = Thread.currentThread().getContextClassLoader().getResource(
            "missions" + File.separator + "missions.yaml");
 
    private final ItemReader itemReader;

    /**
     * 
     */
    public MissionFactoryImpl() {
        this.itemReader = new ItemReaderImpl();    
    }

    /**
     * 
     */
    @Override
    public void initialize() {
            try {
                this.itemReader.setItemPath(itemPath.toURI());
            } catch (IOException | URISyntaxException e) {
                
            }
    }

    /**
     * 
     */
    @Override
    public Mission houdini(){
        return new Houdini(itemReader.getDescription("HOUDINI"),
                itemReader.getPrice("HOUDINI"),
                itemReader.getMissionType("HOUDINI"));
    }

    /**
     * 
     */
    @Override
    public Mission culturalBaggage() {
        return new Houdini(itemReader.getDescription("CULTURALBAGGAGE"),
                itemReader.getPrice("CULTURALBAGGAGE"),
                itemReader.getMissionType("CULTURALBAGGAGE"));
    }
}
