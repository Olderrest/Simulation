package com.stasuma.service.impl;

import com.stasuma.db.dao.SimulationAppInfoDao;
import com.stasuma.db.dao.impl.SimulationAppInfoDaoImpl;
import com.stasuma.objects.SimulationInfo;
import com.stasuma.service.SimulationAppInfoService;

import java.util.Set;

public class SimulationAppInfoServiceImpl implements SimulationAppInfoService {

    private SimulationAppInfoDao simulationInfoDao = new SimulationAppInfoDaoImpl();

    @Override
    public void add(SimulationInfo info){
        simulationInfoDao.add(info);
    }

    @Override
    public void addAll(Set<SimulationInfo> infos){
        simulationInfoDao.addAll(infos);
    }

    @Override
    public void deleteAll(){
        simulationInfoDao.deleteAll();
    }
}
