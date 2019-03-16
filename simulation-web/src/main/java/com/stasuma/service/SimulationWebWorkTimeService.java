package com.stasuma.service;

import com.stasuma.model.*;

import java.util.List;

public interface SimulationWebWorkTimeService {

    SimulationWorkTimeWeb getSimulationWorkTime();

    SimulationWorkTimeForSelect getSimulationWorkTimeForSelect(List<Route> routes, List<Bus> buses, List<Stop> stops);

    void delete();
}
