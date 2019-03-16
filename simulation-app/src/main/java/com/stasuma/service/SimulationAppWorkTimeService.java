package com.stasuma.service;

import com.stasuma.objects.SimulationWorkTime;

public interface SimulationAppWorkTimeService {

    void add(SimulationWorkTime data);

    void delete();

    SimulationWorkTime get();
}
