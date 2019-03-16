package com.stasuma.service.impl;


import com.stasuma.dao.SimulationWorkTimeDao;
import com.stasuma.model.*;
import com.stasuma.service.SimulationWebWorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("simulationWorkTimeService")
@Transactional
public class SimulationWebWorkTimeServiceImpl implements SimulationWebWorkTimeService {

    private SimulationWorkTimeDao simulationWorkTimeDao;

    @Autowired
    @Qualifier("simulationWorkTimeDao")
    public void setDataDao(SimulationWorkTimeDao simulationWorkTimeDao) {
        this.simulationWorkTimeDao = simulationWorkTimeDao;
    }

    public SimulationWorkTimeWeb getSimulationWorkTime(){
        return simulationWorkTimeDao.getSimulationWorkTime();
    }

    public SimulationWorkTimeForSelect getSimulationWorkTimeForSelect(List<Route> routes, List<Bus> buses, List<Stop> stops){
        SimulationWorkTimeWeb data = simulationWorkTimeDao.getSimulationWorkTime();
        SimulationWorkTimeForSelect dataForSelect = new SimulationWorkTimeForSelect();
        if (data != null) {
            dataForSelect.setYears(getYears(data));
            dataForSelect.setMonths(getMonths(data));
            dataForSelect.setDays(getDays());
            dataForSelect.setHours(getHours());
            dataForSelect.setBuses(getBusesNumbers(buses));
            dataForSelect.setRoutes(getRoutesNumbers(routes));
            dataForSelect.setStops(getStopsNumbers(stops));
        }
        return dataForSelect;
    }

    public void delete(){
        simulationWorkTimeDao.delete();
    }


    private List<Integer> getYears(SimulationWorkTimeWeb simulationWorkTimeWeb){
        List<Integer> years = new ArrayList<>();
        if (simulationWorkTimeWeb.getMonth() > 0) {
            for (int i = 0; i < simulationWorkTimeWeb.getYear() + 1; i++) {
                years.add(i + 1);
            }
        }else {
            for (int i = 0; i < simulationWorkTimeWeb.getYear(); i++) {
                years.add(i + 1);
            }
        }
        return years;
    }

    private List<Integer> getMonths(SimulationWorkTimeWeb simulationWorkTimeWeb){
        List<Integer> months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            months.add(i + 1);
        }
        return months;
    }

    private List<Integer> getDays(){
        List<Integer> days = new ArrayList<>();
        for (int i = 0; i < 31; i++) {
            days.add(i + 1);
        }
        return days;
    }

    private List<Integer> getHours(){
        List<Integer> hours = new ArrayList<>();
        for (int i = 4; i < 22; i++) {
            hours.add(i + 1);
        }
        return hours;
    }

    private List<Integer> getBusesNumbers(List<Bus> buses){
        List<Integer> numbers = new ArrayList<>();
        for (Bus bus: buses) {
            numbers.add(bus.getId());
        }
        return numbers;
    }

    private List<Integer> getStopsNumbers(List<Stop> stops){
        List<Integer> numbers = new ArrayList<>();
        for (Stop stop: stops) {
            numbers.add(stop.getId());
        }
        return numbers;
    }

    private List<Integer> getRoutesNumbers(List<Route> routes){
        List<Integer> numbers = new ArrayList<>();
        for (Route route: routes) {
            numbers.add(route.getId());
        }
        return numbers;
    }

}
