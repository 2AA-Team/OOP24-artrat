package it.unibo.artrat.model.impl.missioncenter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.missioncenter.MissionFactory;
import it.unibo.artrat.model.impl.missioncenter.missions.CulturalBaggage;
import it.unibo.artrat.model.impl.missioncenter.missions.RatRace;
import it.unibo.artrat.model.impl.missioncenter.missions.TheRatOfWallStreet;
import it.unibo.artrat.utils.api.MissionReader;
import it.unibo.artrat.utils.impl.MissionReaderImpl;

/**
 * MissionFactory implementation class.
 * 
 * @author Manuel Benagli.
 */
public class MissionFactoryImpl implements MissionFactory {
    private static final String THERATOFWALLSTREET = "THERATOFWALLSTREET";
    private static final String CULTURAL_BAGGAGE = "CULTURALBAGGAGE";
    private static final String RATRACE = "RATRACE";
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionFactoryImpl.class);

    private final InputStream missionPath = Thread.currentThread().getContextClassLoader().getResourceAsStream(
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
            this.missionReader.setPath(missionPath);
        } catch (IOException err) {
            LOGGER.error("Error from Mission Reader", err);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission theRatOfWallStreet() {
        return new TheRatOfWallStreet(missionReader.getName(THERATOFWALLSTREET),
                missionReader.getDescription(THERATOFWALLSTREET),
                false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission culturalBaggage() {
        return new CulturalBaggage(missionReader.getName(CULTURAL_BAGGAGE),
                missionReader.getDescription(CULTURAL_BAGGAGE), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mission ratRace() {
        return new RatRace(missionReader.getName(RATRACE),
                missionReader.getDescription(RATRACE), false);
    }
}
