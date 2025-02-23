package it.unibo.artrat.utils.impl;

import java.util.Set;
import it.unibo.artrat.utils.api.MissionReader;

/**
 * 
 */
public class MissionReaderImpl extends AbstractReader implements MissionReader {

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName(String nameOfMission) {
        return super.getSpecificFiled(nameOfMission, "name");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription(String nameOfMission) {
        return super.getSpecificFiled(nameOfMission, "desc");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getReward(String nameOfMission) {
        return Double.parseDouble(super.getSpecificFiled(nameOfMission, "reward"));    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> getAllMissionName() {
        return super.getKeySetMap();
    }    
}
