package it.unibo.artrat.model.impl.missioncenter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.missioncenter.MissionFactory;
import it.unibo.artrat.model.impl.missioncenter.missions.CulturalBaggage;
import it.unibo.artrat.model.impl.missioncenter.missions.Houdini;
import it.unibo.artrat.utils.api.MissionReader;
import it.unibo.artrat.utils.impl.MissionReaderImpl;

/**
 * MissionFactory implementation class.
 */
public class MissionFactoryImpl implements MissionFactory {
    private static final String HOUDINI = "HOUDINI";
    private static final String CULTURAL_BAGGAGE = "CULTURALBAGGAGE";
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionFactoryImpl.class);

    private final URL missionPath = Thread.currentThread().getContextClassLoader().getResource(
            "missions" + File.separator + "missions.yaml");

    private final MissionReader missionReader;

    /**
     * MissionFactory constructor.
     */
    public MissionFactoryImpl() {
        this.missionReader = new MissionReaderImpl();    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        try {
            this.missionReader.setPath(missionPath.toURI());
        } catch (IOException | URISyntaxException err) {
            LOGGER.error("Error from Mission Reader", err);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission houdini() {
        return new Houdini(missionReader.getName(HOUDINI),
            missionReader.getDescription(HOUDINI));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission culturalBaggage() {
        return new CulturalBaggage(missionReader.getName(CULTURAL_BAGGAGE),
            missionReader.getDescription(CULTURAL_BAGGAGE));
    }
}
