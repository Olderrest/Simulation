package com.stasuma.dao.impl;

import com.stasuma.dao.SimulationWebInfoDao;
import com.stasuma.model.SimulationInfo;
import com.stasuma.model.SimulationInfoQuery;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class SimulationWebInfoDaoImpl implements SimulationWebInfoDao {
    private static final Logger LOGGER = Logger.getLogger(SimulationWebInfoDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<SimulationInfo> getSpecificBusInfos(SimulationInfoQuery simulationInfoQuery) {
        String sql = processSql(simulationInfoQuery);
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        List<SimulationInfo> simulationInfoList = query.list();
        if (simulationInfoList.size() > 0){
            LOGGER.trace("Simulation info found.");
        }else {
            LOGGER.trace("Simulation info not found.");
        }
        return simulationInfoList;
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM SimulationInfo ");
        query.executeUpdate();
    }

    private String processSql(SimulationInfoQuery infoQuery) {
        swapToCorrectQuery(infoQuery);
        StringBuilder resultQuery = new StringBuilder("FROM SimulationInfo WHERE ");
        if (infoQuery.getYearFrom() != 0) {
            if ((infoQuery.getYearFrom() != -1) && (infoQuery.getYearUntil() != -1)) {
                resultQuery.append("year BETWEEN ").append(infoQuery.getYearFrom()).append(" AND ").append(infoQuery.getYearUntil()).append(" AND ");
            } else {
                resultQuery.append("year = ").append(infoQuery.getYearFrom()).append(" AND ");
            }
        }
        if (infoQuery.getMonthFrom() != 0) {
            if ((infoQuery.getMonthFrom() != -1) && (infoQuery.getMonthUntil() != -1)) {
                resultQuery.append("month BETWEEN ").append(infoQuery.getMonthFrom()).append(" AND ").append(infoQuery.getMonthUntil()).append(" AND ");
            } else {
                resultQuery.append("month = ").append(infoQuery.getMonthFrom()).append(" AND ");
            }
        }

        if (infoQuery.getDayFrom() != 0) {
            if ((infoQuery.getDayFrom() != -1) && (infoQuery.getDayUntil() != -1)) {
                resultQuery.append("day BETWEEN ").append(infoQuery.getDayFrom()).append(" AND ").append(infoQuery.getDayUntil()).append(" AND ");
            } else {
                resultQuery.append("day = ").append(infoQuery.getDayFrom()).append(" AND ");
            }
        }

        if (infoQuery.getHourFrom() != 0) {
            if ((infoQuery.getHourFrom() != -1) && (infoQuery.getHourUntil() != -1)) {
                resultQuery.append("hour BETWEEN ").append(infoQuery.getHourFrom()).append(" AND ").append(infoQuery.getHourUntil()).append(" AND ");
            } else {
                resultQuery.append("hour = ").append(infoQuery.getHourFrom()).append(" AND ");
            }
        }

        if ((infoQuery.getBusIdFrom() != 0) && (infoQuery.getBusIdFrom() != -1)) {
            if ((infoQuery.getBusIdFrom() != 0) && (infoQuery.getBusIdUntil() != 0)) {
                resultQuery.append("bus_id BETWEEN ").append(infoQuery.getBusIdFrom()).append(" AND ").append(infoQuery.getBusIdUntil());
            } else {
                resultQuery.append("bus_id = ").append(infoQuery.getBusIdFrom());
            }
        }

        if ((infoQuery.getRouteIdFrom() != 0) && (infoQuery.getRouteIdFrom() != -1)) {
            if ((infoQuery.getRouteIdFrom() != 0) && (infoQuery.getRouteIdUntil() != 0)) {
                resultQuery.append("route_id BETWEEN ").append(infoQuery.getRouteIdFrom()).append(" AND ").append(infoQuery.getRouteIdUntil());
            } else {
                resultQuery.append("route_id = ").append(infoQuery.getRouteIdFrom());
            }
        }

        if ((infoQuery.getStopIdFrom() != 0) && (infoQuery.getStopIdFrom() != -1)) {
            if ((infoQuery.getStopIdFrom() != 0) && (infoQuery.getStopIdUntil() != 0)) {
                resultQuery.append("stop_id BETWEEN ").append(infoQuery.getStopIdFrom()).append(" AND ").append(infoQuery.getStopIdUntil());
            } else {
                resultQuery.append("stop_id = ").append(infoQuery.getStopIdFrom());
            }
        }
        return resultQuery.toString();
    }

    private void swapToCorrectQuery(SimulationInfoQuery simulationInfoQuery) {
        if (simulationInfoQuery.getYearUntil() > 0) {
            if (simulationInfoQuery.getYearFrom() > simulationInfoQuery.getYearUntil()) {
                int tmp = simulationInfoQuery.getYearFrom();
                simulationInfoQuery.setYearFrom(simulationInfoQuery.getYearUntil());
                simulationInfoQuery.setYearUntil(tmp);
            }
        }
        if (simulationInfoQuery.getMonthUntil() > 0) {
            if (simulationInfoQuery.getMonthFrom() > simulationInfoQuery.getMonthUntil()) {
                int tmp = simulationInfoQuery.getMonthFrom();
                simulationInfoQuery.setMonthFrom(simulationInfoQuery.getMonthUntil());
                simulationInfoQuery.setMonthUntil(tmp);
            }
        }
        if (simulationInfoQuery.getDayUntil() > 0) {
            if (simulationInfoQuery.getDayFrom() > simulationInfoQuery.getDayUntil()) {
                int tmp = simulationInfoQuery.getDayFrom();
                simulationInfoQuery.setDayFrom(simulationInfoQuery.getDayUntil());
                simulationInfoQuery.setDayUntil(tmp);
            }
        }
        if (simulationInfoQuery.getHourUntil() > 0) {
            if (simulationInfoQuery.getHourFrom() > simulationInfoQuery.getHourUntil()) {
                int tmp = simulationInfoQuery.getHourFrom();
                simulationInfoQuery.setHourFrom(simulationInfoQuery.getHourUntil());
                simulationInfoQuery.setHourUntil(tmp);
            }
        }
        if (simulationInfoQuery.getBusIdUntil() > 0) {
            if (simulationInfoQuery.getBusIdFrom() > simulationInfoQuery.getBusIdUntil()) {
                int tmp = simulationInfoQuery.getBusIdFrom();
                simulationInfoQuery.setBusIdFrom(simulationInfoQuery.getBusIdUntil());
                simulationInfoQuery.setBusIdUntil(tmp);
            }
        }
        if (simulationInfoQuery.getStopIdUntil() > 0) {
            if (simulationInfoQuery.getStopIdFrom() > simulationInfoQuery.getStopIdUntil()) {
                int tmp = simulationInfoQuery.getStopIdFrom();
                simulationInfoQuery.setStopIdFrom(simulationInfoQuery.getStopIdUntil());
                simulationInfoQuery.setStopIdUntil(tmp);
            }
        }
        if (simulationInfoQuery.getRouteIdUntil() > 0) {
            if (simulationInfoQuery.getRouteIdFrom() > simulationInfoQuery.getRouteIdUntil()) {
                int tmp = simulationInfoQuery.getRouteIdFrom();
                simulationInfoQuery.setRouteIdFrom(simulationInfoQuery.getRouteIdUntil());
                simulationInfoQuery.setRouteIdUntil(tmp);
            }
        }
    }
}
