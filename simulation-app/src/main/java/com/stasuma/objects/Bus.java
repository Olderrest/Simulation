package com.stasuma.objects;


import com.stasuma.util.CsvUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Bus {
    private static final Logger busLogger = Logger.getLogger(Bus.class);

    private int id;
    private List<Passenger> passengers = Collections.synchronizedList(new ArrayList<>());
    private int size;
    private int currentStopIndex = 0;
    private long timeToNextStop;
    private int routeId;
    private boolean work = false;
    private boolean reachedLastStop = false;
    private int passengersPerTransit;
    private int loadPercentage;
    private int loadCount = 0;

    public Bus() {

    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Bus(int size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isWork() {
        return work;
    }

    public boolean isOnWay() {
        return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) <= timeToNextStop;
    }

    public void setTimeToNextStop(int time) {
        this.timeToNextStop = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) + time;

    }

    public int getLoadPercentage() {
        return loadPercentage;
    }

    public void setLoadPercentage(int loadPercentage) {
        this.loadPercentage = loadPercentage;
    }

    public synchronized int processBusStop(List<Stop> stops, Route route) {
        int time = 0;
        if (work) { // если автобус выехал на маршрут
            increaseLoadPercent();
            Stop currentStop = stops.get(currentStopIndex);
            currentStop.increaseWorkload(currentStop.getQueue().size());
            time = currentStop.getTime();
            SimulationTime.StopTime stopTime = SimulationTime.getStopTime(time);
            busLogger.debug("------Bus №" + getId() + " at the stop №" + currentStop.getId() + "-------");
            busLogger.debug("Time in simulation: " + stopTime);
            busLogger.debug("in bus: " + passengers.size());
            busLogger.debug("in stop: " + currentStop.getQueue().size());
            int passengersOut = checkRequiredStop(currentStop.getId()); // проверяем всех пасажиров кому выходить на текущей остановке
            int passengersIn = boardPassengers(currentStop); // сажаем всех пассажиров в автобус
            processCurrentStopIndex(stops.size(), route, passengers.size()); // устанавливаем индекс следующей остановки
            processInfo(stopTime.getYear(), stopTime.getMonth(), stopTime.getDay(),
                    stopTime.getHour(), stopTime.getMinute(), currentStop.getId(),
                    route.getId(), getId(), passengersIn, passengersOut, passengers.size());
            setTimeToNextStop(stops.get(currentStopIndex).getTime()); // устанавливаем время до следующей остановки

        } else { // если автобус не выехал на маршрут
            work = true; // устанавливаем флаг
            Stop currentStop = stops.get(currentStopIndex); // получаем остановку до которой ехать автобусу
            setTimeToNextStop(currentStop.getTime()); // устанавливаем время езды до остановки
        }
        busLogger.debug("Bus goes to next stop");
        return time;
    }

    private void processCurrentStopIndex(int stopsSize, Route route, int passengersNumber) {
        if (route.getRouteType() == RouteType.CIRCULAR) {
            if (currentStopIndex == stopsSize - 1) {
                currentStopIndex = 0;
                route.increaseWorkload(passengersPerTransit);
                passengersPerTransit = 0;
            } else {
                currentStopIndex++;
                passengersPerTransit += passengersNumber;
            }
        } else {
            if (reachedLastStop) {
                if (currentStopIndex == 0) {
                    reachedLastStop = false;
                    route.increaseWorkload(passengersPerTransit);
                    passengersPerTransit = 0;
                    currentStopIndex = 1;
                } else {
                    currentStopIndex--;
                    passengersPerTransit += passengersNumber;
                }
            } else {
                if (currentStopIndex == stopsSize - 1) {
                    currentStopIndex = stopsSize - 2;
                    reachedLastStop = true;
                    route.increaseWorkload(passengersPerTransit);
                    passengersPerTransit = 0;
                } else {
                    currentStopIndex++;
                    passengersPerTransit += passengersNumber;
                }
            }
        }
    }

    private void increaseLoadPercent() {
        loadPercentage += (passengers.size() * 100) / size;
        loadCount++;
    }

    public void calculateLoadPercent() {
        loadPercentage = loadPercentage / loadCount;
    }

    private synchronized int boardPassengers(Stop currentStop) {
        int count = 0;
        while (currentStop.getQueue().size() != 0) {
            if (passengers.size() < size) {
                passengers.add(currentStop.getQueue().poll());
                count++;
            } else {
                busLogger.debug("Passenger denied boarding");
                break;
            }
        }
        return count;
    }

    private synchronized int checkRequiredStop(int id) {
        int count = 0;
        Iterator<Passenger> it = passengers.iterator();
        while (it.hasNext()) {
            Passenger passenger = it.next();
            if (passenger != null) {
                if (passenger.getRequiredStopNumber() == id) {
                    it.remove();
                    count++;
                    busLogger.debug("Passenger left the bus");
                }
            }
        }
        return count;
    }

    public void reset() {
        work = false;
        currentStopIndex = 0;
        reachedLastStop = false;
        passengers = Collections.synchronizedList(new ArrayList<>());
        timeToNextStop = 0;
    }

    private void processInfo(int year, int month, int day, int hour, int minutes, int stopId, int routeId, int busId, int passIn, int passOut, int passInBus) {
        SimulationInfo info = new SimulationInfo(year, month, day, hour, minutes, stopId, routeId, busId, passIn, passOut, passInBus);
        CsvUtil.writeToCsv(info);
    }
}
