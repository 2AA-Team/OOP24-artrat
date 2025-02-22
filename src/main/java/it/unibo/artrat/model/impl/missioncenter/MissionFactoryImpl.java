package it.unibo.artrat.model.impl.missioncenter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.missioncenter.MissionFactory;
import it.unibo.artrat.utils.api.MissionReader;
import it.unibo.artrat.utils.impl.MissionReaderImpl;

/**
 * 
 */
public class MissionFactoryImpl implements MissionFactory {
    
    private static final String HOUDINI = "HOUDINI";
    private static final String CULTURAL_BAGGAGE = "CULTURAL BAGGAGE";

    private final URL missionPath = Thread.currentThread().getContextClassLoader().getResource(
            "missions" + File.separator + "missions.yaml");
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionFactoryImpl.class);

    private final MissionReader missionReader;

    /**
     * 
     */
    public MissionFactoryImpl() {
        this.missionReader = new MissionReaderImpl();    
    }

    /**
     * 
     */
    @Override
    public void initialize() {
        try {
            this.missionReader.setPath(missionPath.toURI());
        } catch (IOException | URISyntaxException err) {
            LOGGER.error("Error from Mission reader", err);
        }
    }

    /**
     * 
     */
    @Override
    public Mission houdini(){
        return new Houdini(missionReader.getName(HOUDINI),
                missionReader.getDescription(HOUDINI),
                missionReader.getReward(HOUDINI),
                missionReader.getMissionType(HOUDINI));
    }

    /**
     * 
     */
    @Override
    public Mission culturalBaggage() {
        return new CulturalBaggage(missionReader.getName(CULTURAL_BAGGAGE),
                missionReader.getDescription(CULTURAL_BAGGAGE),
                missionReader.getReward(CULTURAL_BAGGAGE),
                missionReader.getMissionType(CULTURAL_BAGGAGE));   
    }
}
