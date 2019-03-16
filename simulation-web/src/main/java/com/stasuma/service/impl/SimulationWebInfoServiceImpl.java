package com.stasuma.service.impl;


import com.stasuma.dao.SimulationWebInfoDao;
import com.stasuma.model.SimulationInfo;
import com.stasuma.model.SimulationInfoQuery;
import com.stasuma.service.SimulationWebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("busInfoService")
@Transactional
public class SimulationWebInfoServiceImpl implements SimulationWebInfoService {

    private SimulationWebInfoDao simulationWebInfoDao;

    @Autowired
    public void setSimulationWebInfoDao(SimulationWebInfoDao simulationWebInfoDao) {
        this.simulationWebInfoDao = simulationWebInfoDao;
    }

    public List<SimulationInfo> getSpecificBusInfos(SimulationInfoQuery simulationInfoQuery){
        return simulationWebInfoDao.getSpecificBusInfos(simulationInfoQuery);
    }

    public void deleteAllInfo(){
        simulationWebInfoDao.deleteAll();
    }

}
