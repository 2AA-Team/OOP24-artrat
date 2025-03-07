package it.unibo.artrat.model.impl.missioncenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unibo.artrat.model.api.missioncenter.Mission;
import it.unibo.artrat.model.api.missioncenter.MissionCenter;
import it.unibo.artrat.model.api.missioncenter.MissionFactory;
import it.unibo.artrat.utils.api.MissionReader;
import it.unibo.artrat.utils.impl.MissionReaderImpl;

/**
 * MissionCenter implementation class.
 * 
 * @author Manuel Benagli.
 */
public class MissionCenterImpl implements MissionCenter {
    private final InputStream missionPath = Thread.currentThread().getContextClassLoader().getResourceAsStream(
            "missions/missions.yaml");

    private final MissionFactory missionFactory;
    private final List<Mission> missionsToRedeem;
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionCenterImpl.class);
    private final MissionReader missionReader;

    /**
     * MissionCenter defualt constructor.
     */
    public MissionCenterImpl() {
        this.missionsToRedeem = new ArrayList<>();
        this.missionFactory = new MissionFactoryImpl();
        this.missionReader = new MissionReaderImpl();
    }

    /**
     * MissionCenter constructor.
     * 
     * @param missionCenter MissionCenter interface.
     */
    public MissionCenterImpl(final MissionCenter missionCenter) {
        this.missionsToRedeem = new ArrayList<>();
        this.missionsToRedeem.addAll(missionCenter.getMissionList());
        this.missionFactory = new MissionFactoryImpl();
        this.missionReader = new MissionReaderImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mission> getMissionList() {
        return new ArrayList<>(missionsToRedeem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initMissionCenter() {
        try {
            missionReader.setPath(missionPath);
            this.missionFactory.initialize();
        } catch (IOException err) {
            LOGGER.error("MissionCenterImpl class thrown an error : ", err);
        }
        for (final String mission : missionReader.getAllMissionName()) {
            this.missionsToRedeem.add(createMission(mission));
        }
    }

    /**
     * This private method has made to create my missions using missionFactory.
     *
     * @param missionName mission's name.
     * @return a mission using MissionFactory.
     * @throws IllegalArgumentException if the passed missionName is not compatible.
     */
    private Mission createMission(final String missionName) {
        switch (missionName) {
            case "THERATOFWALLSTREET":
                return missionFactory.theRatOfWallStreet();
            case "CULTURALBAGGAGE":
                return missionFactory.culturalBaggage();
            case "RATRACE":
                return missionFactory.ratRace();
            default:
                break;
        }
        throw new IllegalArgumentException("The passed mission name is not compatible");
    }
}
