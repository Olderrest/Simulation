package com.stasuma.dao.impl;


import com.stasuma.dao.SimulationWorkTimeDao;
import com.stasuma.model.SimulationWorkTimeWeb;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;

public class SimulationWebWorkTimeDaoImpl implements SimulationWorkTimeDao {
    private static final Logger LOGGER = Logger.getLogger(SimulationWebWorkTimeDaoImpl.class);


    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public SimulationWorkTimeWeb getSimulationWorkTime() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM SimulationWorkTimeWeb");
        LOGGER.trace("Simulation work time successfully found.");
        return (SimulationWorkTimeWeb) query.uniqueResult();
    }

    @Override
    public void update(SimulationWorkTimeWeb simulationWorkTimeWeb) {
        Session session = sessionFactory.getCurrentSession();
        SimulationWorkTimeWeb updTime = (SimulationWorkTimeWeb) session.get(SimulationWorkTimeWeb.class, new Integer(simulationWorkTimeWeb.getId()));
        updTime.setYear(simulationWorkTimeWeb.getYear());
        updTime.setMonth(simulationWorkTimeWeb.getMonth());
        updTime.setDay(simulationWorkTimeWeb.getDay());
        updTime.setStartSimulationTime(simulationWorkTimeWeb.getStartSimulationTime());
        updTime.setEndSimulationTime(simulationWorkTimeWeb.getEndSimulationTime());
        updTime.setRouteCount(simulationWorkTimeWeb.getRouteCount());
        updTime.setBusCount(simulationWorkTimeWeb.getBusCount());
        updTime.setStopCount(simulationWorkTimeWeb.getStopCount());
        session.save(updTime);
        LOGGER.trace("Simulation work time successfully updated.");
    }

    @Override
    public void delete(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM SimulationWorkTimeWeb");
        query.executeUpdate();
        LOGGER.trace("Simulation work time successfully deleted.");
    }
}
