package com.stasuma.objects;


import com.stasuma.service.impl.NetworkService;
import com.stasuma.util.Constants;
import com.stasuma.util.CsvUtil;
import com.stasuma.util.XmlSaxParser;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Network {
    private static final Logger networkLogger = Logger.getLogger(Network.class);

    private static List<Route> routes;
    private static Set<Stop> stops = new HashSet<>();
    private static String startSimulationTime;
    private static String endSimulationTime;
    private static Map<Stop, Route> stopRouteMap;
    private static SimulationTime simulationTime = SimulationTime.getNewStartTime();
    private static boolean resetFlag = true;
    private static SimulationWorkTime simulationWorkTime = new SimulationWorkTime();

    public Network() {
    }

    public void start() {
        removeOldFiles();
        List<Route> routeList = XmlSaxParser.parse();
        routes = NetworkService.saveAndReturnRoutes(routeList);
        int years = XmlSaxParser.getYears();
        int months = XmlSaxParser.getMonths();
        int days = XmlSaxParser.getDays();
        stops = getAllStops();
        simulationTime.setSimulationFinalData(years, months, days);
        stopRouteMap = getRouteContainStopMap();
        processSimulationWorkTime(years, months, days, routes);
        startSimulationTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        simulationWorkTime.setStartSimulation(startSimulationTime);
        new PassengerGenerator();
        new QueueCleaner();
        new BusRunner();
        networkLogger.info("Simulation start");
    }

    public void restart() {
        NetworkService.deleteAllBusInfo();
        removeOldFiles();
        SimulationWorkTime workTime = NetworkService.getSimulationWorkTime();
        int years = workTime.getYears();
        int months = workTime.getMonths();
        int days = workTime.getDays();
        routes = NetworkService.getRoutes();
        stops = getAllStops();
        simulationTime.setSimulationFinalData(years, months, days);
        stopRouteMap = getRouteContainStopMap();
        processSimulationWorkTime(years, months, days, routes);
        startSimulationTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        simulationWorkTime.setStartSimulation(startSimulationTime);
        NetworkService.deleteSimulationWorkTime();
        new PassengerGenerator();
        new QueueCleaner();
        new BusRunner();
        networkLogger.info("Simulation start");
    }

    private void processSimulationWorkTime(int year, int month, int day, List<Route> routes) {
        simulationWorkTime.setYears(year);
        simulationWorkTime.setMonths(month);
        simulationWorkTime.setDays(day);
        simulationWorkTime.setStopCount(stops.size());
        simulationWorkTime.setRouteCount(routes.size());
        int busCount = 0;
        for (Route route : routes) {
            busCount += route.getBuses().size();
        }
        simulationWorkTime.setBusCount(busCount);
    }

    private void removeOldFiles() {
        String logs = Constants.PATH_TO_APP_FOLDER + File.separator + Constants.PATH_TO_LOGS + File.separator;
        clearLogs(logs);
        CsvUtil.deleteCsv();
    }

    private void clearLogs(String path) {
        File dir = new File(path);
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                if (!file.getName().equals(Constants.LOG_FILE_NAME)) {
                    file.delete();
                }
            }
        }
        File logFile = new File(path + File.separator + Constants.LOG_FILE_NAME);
        try (PrintWriter pw = new PrintWriter(logFile)) {
            pw.write("");
            networkLogger.debug("Log file: " + logFile.getName() + " cleaned");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Map<Stop, Route> getRouteContainStopMap() {
        Map<Stop, Route> map = new HashMap<>();
        for (Stop stop : stops) {
            for (Route route : routes) {
                if (route.getStops().contains(stop)) {
                    map.put(stop, route);
                    break;
                }
            }
        }
        return map;
    }

    private static void reset() {
        resetBuses();
        clearQueues();
    }

    private static void clearQueues() {
        for (Stop stop : stops) {
            stop.getQueue().removeAll();
        }
        networkLogger.debug("Passengers removed from stops");
    }

    private static void resetBuses() {
        for (Route route : routes) {
            for (Bus bus : route.getBuses()) {
                bus.reset();
            }
        }
        networkLogger.debug("Buses reset");
    }

    public String getStartTime() {
        return startSimulationTime;
    }

    public String getEndTime() {
        return endSimulationTime;
    }

    private Set<Stop> getAllStops() {
        Set<Stop> stopSet = new HashSet<>();
        for (Route route : routes) {
            stopSet.addAll(route.getStops());
        }
        return stopSet;
    }

    private static void processWorkload() {
        NetworkService.calculateWorkloadAndUpdate(stops, routes);
        networkLogger.info("Workload updated.");
    }

    private static void delay() {
        try {
            TimeUnit.MILLISECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkWaitingThreads(Thread t1, Thread t2) {
        return ((t1.getState() == Thread.State.TIMED_WAITING) && (t2.getState() == Thread.State.TIMED_WAITING));
    }

    private static class PassengerGenerator implements Runnable {
        private double lowCoeffValue = 0.1;
        private double mediumCoeffValue = 0.3;
        private double highCoeffValue = 0.5;
        private int lowLoopSize = 2;
        private int mediumLoopSize = 4;
        private int highLoopSize = 6;
        private double coeff;
        private int loopSize;
        private static Thread t;

        private PassengerGenerator() {
            t = new Thread(this, "PassengerGenerator");
            t.start();
        }

        @Override
        public void run() {
            delay();
            while (!Thread.interrupted()) {
                if (!SimulationTime.checkEndSimulation()) {
                    if (checkWaitingThreads(QueueCleaner.t, BusRunner.t)) {
                        if (simulationTime.checkWorkTime()) {
                            try {
                                coeff = simulationTime.calculateCoeff(highCoeffValue, mediumCoeffValue, lowCoeffValue);
                                loopSize = calculateLoopSize(coeff);
                                networkLogger.trace("Generator start");
                                for (int i = 0; i < loopSize; i++) {
                                    for (Stop stop : stops) {
                                        if (coeff > Math.random()) {
                                            stop.getQueue().add(new Passenger(generateRequiredStop(stop)), generateTimeToWait());
                                        }
                                    }
                                }
                                if (Thread.interrupted()) {
                                    return;
                                }
                                networkLogger.trace("Generator finish");
                                TimeUnit.NANOSECONDS.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            TimeUnit.NANOSECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    networkLogger.trace("Generator dead");
                    t.interrupt();
                    break;
                }
            }
        }

        private int generateRequiredStop(Stop stop) {
            Route route = stopRouteMap.get(stop);
            List<Stop> list = new ArrayList<>(new TreeSet<>(route.getStops()));
            int max = list.get(list.size() - 1).getId();
            int min = list.get(0).getId();
            max -= min;
            int number = (int) (Math.random() * max + 1) + min;
            if (number == stop.getId()) {
                number = generateRequiredStop(stop);
            }
            return number;
        }

        private int generateTimeToWait() {
            int min = 600000;
            int max = 650000;
            max -= min;
            return (int) (Math.random() * max + 1) + min;
        }

        private int calculateLoopSize(double coeff) {
            if (coeff == highCoeffValue) {
                return highLoopSize;
            } else if (coeff == mediumCoeffValue) {
                return mediumLoopSize;
            } else {
                return lowLoopSize;
            }
        }
    }

    private static class QueueCleaner implements Runnable {
        private static Thread t;

        private QueueCleaner() {
            t = new Thread(this, "QueueCleaner");
            t.start();
        }

        @Override
        public void run() {
            delay();
            while (!Thread.interrupted()) {
                if (!SimulationTime.checkEndSimulation()) {
                    if (checkWaitingThreads(PassengerGenerator.t, BusRunner.t)) {
                        if (simulationTime.checkWorkTime()) {
                            try {
                                networkLogger.trace("Cleaner start");
                                for (Stop stop : stops) {
                                    Set<Passenger> set = new HashSet<>(stop.getQueue().getAllPassengers());
                                    for (Passenger passenger : set) {
                                        if (!isValid(passenger, stop)) {
                                            stop.getQueue().remove(passenger);
                                            networkLogger.debug("Passenger removed");
                                        }
                                    }
                                }
                                if (Thread.interrupted()) {
                                    return;
                                }
                                networkLogger.trace("Cleaner finish");
                                TimeUnit.NANOSECONDS.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            TimeUnit.NANOSECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    networkLogger.trace("Cleaner dead");
                    t.interrupt();
                    break;
                }
            }
        }

        private boolean isValid(Passenger passenger, Stop stop) {
            long time = stop.getQueue().get(passenger);
            return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) <= time;
        }
    }

    private static class BusRunner implements Runnable {
        private static Thread t;

        private BusRunner() {
            t = new Thread(this, "BusRunner");
            t.start();
        }

        @Override
        public void run() {
            delay();
            while (!Thread.interrupted()) {
                if (!SimulationTime.checkEndSimulation()) {
                    if (checkWaitingThreads(QueueCleaner.t, PassengerGenerator.t)) {
                        if (simulationTime.checkWorkTime()) {
                            try {
                                int maxTime = 0;
                                resetFlag = true;
                                networkLogger.trace("Runner start");
                                for (Route route : routes) {
                                    for (Bus bus : route.getBuses()) {
                                        if (bus.isWork()) { // проверяем выехал ли автобус на маршрут
                                            if (!bus.isOnWay()) { // если да, то проверяем добрался ли до остановки
                                                int time = bus.processBusStop(route.getStops(), route); // обрабатываем остановку
                                                if (time > maxTime) {
                                                    maxTime = time;
                                                }
                                            }
                                        } else { // если не выехал на маршрут
                                            if (!route.checkNextStart()) { // проверяем интервал выезда автобусов на маршрут
                                                bus.processBusStop(route.getStops(), route); // обрабатываем остановку
                                                route.updateNextStart();  // устанавливаем интервал
                                            }
                                        }
                                    }
                                }
                                SimulationTime.addMinutes(maxTime);
                                if (Thread.interrupted()) {
                                    return;
                                }
                                networkLogger.trace("Runner finish");
                                TimeUnit.NANOSECONDS.sleep(1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (resetFlag) {
                                reset();
                                resetFlag = false;
                            }
                            SimulationTime.addMinutes(1);
                        }
                    } else {
                        try {
                            TimeUnit.NANOSECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    networkLogger.trace("Runner dead");
                    t.interrupt();
                    networkLogger.info("Simulation finished");
                    endSimulationTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    simulationWorkTime.setEndSimulation(endSimulationTime);
                    NetworkService.saveSimulationWorkTime(simulationWorkTime);
                    simulationTime = SimulationTime.getNewStartTime();
                    break;
                }
            }
            CsvUtil.readCsvAndWriteToDB();
            processWorkload();
        }
    }
}
