package com.stasuma.dao;

import com.stasuma.model.SimulationInfo;
import com.stasuma.model.SimulationInfoQuery;

import java.util.List;
import java.util.Set;

public interface SimulationWebInfoDao {

    List<SimulationInfo> getSpecificBusInfos(SimulationInfoQuery simulationInfoQuery);

    void deleteAll();
}
