package it.unibo.artrat.model.impl.missioncenter;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
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
 * 
 */
public class MissionCenterImpl implements MissionCenter {
    private final URL missionPath = Thread.currentThread().getContextClassLoader().getResource(
            "missions" + File.separator
                    + "missions.yaml");

    private MissionFactory missionFactory;
    private List<Mission> missionsToRedeem;
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionCenterImpl.class);
    
    /**
     * 
     */
    public MissionCenterImpl() {
        this.missionsToRedeem = new ArrayList<>();
    }
    
    /**
     * 
     * @param missionCenter
     */
    public MissionCenterImpl(MissionCenter missionCenter) {
        this.missionsToRedeem = new ArrayList<>();
        this.missionsToRedeem.addAll(missionCenter.getMissionList());
        this.missionFactory = new MissionFactoryImpl();
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
    public void setMissionList(List<Mission> missions) {
        this.missionsToRedeem = new ArrayList<>(missions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean redeemMission(Mission passedMission) {
        throw new UnsupportedOperationException("Unimplemented method 'redeemMission'");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initMissionCenter() {
        final MissionReader missionReader = new MissionReaderImpl();

        try {
            missionReader.setPath(missionPath.toURI());
            this.missionFactory.initialize();
        } catch (IOException | URISyntaxException err) {
            LOGGER.error("MissionCenterImpl class thrown an error : ", err);
        }
        for (final String mission : missionReader.getAllMissionName()) {
            this.missionsToRedeem.add(createMission(mission));
        }
    }

    private Mission createMission(final String missionName){
        switch (missionName) {
            case "HOUDINI":
                return missionFactory.houdini();
            case "CULTURALBAGGAGE":
                return missionFactory.culturalBaggage();
            default:
                break;
        }
        throw new IllegalArgumentException("The passed mission name is not compatible");
    }
}
