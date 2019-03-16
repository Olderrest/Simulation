package com.stasuma.service.impl;

import com.stasuma.db.dao.SimulationAppWorkTimeDao;
import com.stasuma.db.dao.impl.SimulationAppWorkTimeDaoImpl;
import com.stasuma.objects.SimulationWorkTime;
import com.stasuma.service.SimulationAppWorkTimeService;

public class SimulationAppWorkTimeServiceImpl implements SimulationAppWorkTimeService {

    private SimulationAppWorkTimeDao simulationWorkTimeDao = new SimulationAppWorkTimeDaoImpl();

    @Override
    public void add(SimulationWorkTime data) {
        simulationWorkTimeDao.add(data);
    }

    @Override
    public void delete() {
        simulationWorkTimeDao.delete();
    }

    @Override
    public SimulationWorkTime get() {
        return simulationWorkTimeDao.get();
    }
}
