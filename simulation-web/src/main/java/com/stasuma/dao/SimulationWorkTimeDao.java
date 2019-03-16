package com.stasuma.dao;

import com.stasuma.model.SimulationWorkTimeWeb;

public interface SimulationWorkTimeDao {

    SimulationWorkTimeWeb getSimulationWorkTime();

    void update(SimulationWorkTimeWeb simulationWorkTimeWeb);

    void delete();
}
