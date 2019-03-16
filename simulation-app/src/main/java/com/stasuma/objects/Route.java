package com.stasuma.objects;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Route {
    private int id;
    private List<Stop> stops = new ArrayList<>();
    private Set<Bus> buses = new HashSet<>();
    private int interval;
    private long nextStart;
    private RouteType routeType;
    private long averageWorkload;
    private long workloadCount;

    public Route() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public Set<Bus> getBuses() {
        return buses;
    }

    public long getAverageWorkload() {
        return averageWorkload;
    }

    public void setAverageWorkload(long averageWorkload) {
        this.averageWorkload = averageWorkload;
    }

    public void increaseWorkload(int passengers) {
        averageWorkload += passengers;
        workloadCount++;
    }


    public void calculaeteWorkload() {
        averageWorkload = averageWorkload / workloadCount;
    }

    public void setBuses(Set<Bus> buses) {
        this.buses = buses;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean checkNextStart() {
        return TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) < this.nextStart;

    }

    public void updateNextStart() {
        this.nextStart = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) + interval;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", interval=" + interval +
                ", stopsSize=" + stops.size() +
                ", routeType=" + routeType +
                ", busSize=" + buses.size() +
                '}';
    }
}
