package com.stasuma.db.dao;

import com.stasuma.objects.SimulationWorkTime;

public interface SimulationAppWorkTimeDao {

    void add(SimulationWorkTime data);

    void delete();

    SimulationWorkTime get();

}
