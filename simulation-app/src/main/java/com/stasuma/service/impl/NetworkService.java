package com.stasuma.service.impl;

import com.stasuma.objects.*;
import com.stasuma.service.*;

import java.util.List;
import java.util.Set;

public class NetworkService {

    private static final BusAppService BUS_SERVICE = new BusAppServiceImpl();
    private static final StopAppService STOP_SERVICE = new StopAppServiceImpl();
    private static final RouteAppService ROUTE_SERVICE = new RouteAppServiceImpl();
    private static final SimulationAppInfoService SIMULATION_INFO_SERVICE = new SimulationAppInfoServiceImpl();
    private static final SimulationAppWorkTimeService SIMULATION_WORK_TIME_SERVICE = new SimulationAppWorkTimeServiceImpl();

    public static List<Route> saveAndReturnRoutes(List<Route> routeList) {
        for (Route route : routeList) {
            Route insertedRoute = saveRoute(route);
            saveBuses(route);
            for (Stop stop : insertedRoute.getStops()) {
                STOP_SERVICE.add(stop, insertedRoute);
            }
        }
        return ROUTE_SERVICE.findAll();
    }

    private static Route saveRoute(Route route) {
        return ROUTE_SERVICE.add(route);
    }

    private static void saveBuses(Route route) {
        for (Bus bus : route.getBuses()) {
            BUS_SERVICE.add(bus, route);
        }
    }

    public static SimulationWorkTime getSimulationWorkTime() {
        return SIMULATION_WORK_TIME_SERVICE.get();
    }

    public static void deleteAllBusInfo() {
        SIMULATION_INFO_SERVICE.deleteAll();
    }

    public static void deleteSimulationWorkTime(){
        SIMULATION_WORK_TIME_SERVICE.delete();
    }

    public static List<Route> getRoutes() {
        return ROUTE_SERVICE.findAll();
    }

    public static void saveBusInfo(SimulationInfo info) {
        SIMULATION_INFO_SERVICE.add(info);
    }

    public static void saveAllSimulationInfo(Set<SimulationInfo> infos) {
        SIMULATION_INFO_SERVICE.addAll(infos);
    }

    public static void saveSimulationWorkTime(SimulationWorkTime workTime) {
        SIMULATION_WORK_TIME_SERVICE.add(workTime);
    }

    public static void calculateWorkloadAndUpdate(Set<Stop> stops, List<Route> routes) {
        for (Stop stop : stops) {
            stop.calculateWorkload();
            STOP_SERVICE.update(stop);
        }
        for (Route route : routes) {
            route.calculaeteWorkload();
            ROUTE_SERVICE.update(route);
            for (Bus bus : route.getBuses()) {
                bus.calculateLoadPercent();
                BUS_SERVICE.update(bus);
            }
        }
    }
}
