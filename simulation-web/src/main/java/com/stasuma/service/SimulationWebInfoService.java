package com.stasuma.service;

import com.stasuma.model.SimulationInfo;
import com.stasuma.model.SimulationInfoQuery;

import java.util.List;

public interface SimulationWebInfoService {

    List<SimulationInfo> getSpecificBusInfos(SimulationInfoQuery simulationInfoQuery);

    void deleteAllInfo();

}
