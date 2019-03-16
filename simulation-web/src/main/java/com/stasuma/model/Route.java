package com.stasuma.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends Model {

    @Column(name = "stops_number", nullable = false)
    private int stopsNumber;

    @Column(name = "bus_number", nullable = false)
    private int busNumber;

    @Column(name = "move_bus_interval", nullable = false)
    private int interval;

    @Enumerated(EnumType.STRING)
    @Column(name = "route_type", nullable = false)
    private RouteType routeType;

    @Column(name = "avg_workload")
    private int avgWorkload;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bus> buses = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "route_stop",
            joinColumns = {@JoinColumn(name = "route_id")},
            inverseJoinColumns = {@JoinColumn(name = "stop_id")})
    private List<Stop> stops = new ArrayList<>();

    @PreRemove
    private void removeRoutesFromStops() {
        for (Stop s : stops) {
            s.getRoutes().remove(this);
        }
    }

    public Route() {
    }

    public Route(int stopsNumber, RouteType routeType, List<Stop> stops) {
        this.stopsNumber = stopsNumber;
        this.routeType = routeType;
        this.stops = stops;
    }

    public int getStopsNumber() {
        return stopsNumber;
    }

    public void setStopsNumber(int stopsNumber) {
        this.stopsNumber = stopsNumber;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getAvgWorkload() {
        return avgWorkload;
    }

    public void setAvgWorkload(int avgWorkload) {
        this.avgWorkload = avgWorkload;
    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;
    }

    @Override
    public String toString() {
        return "Route{" +
                "stopsNumber=" + stopsNumber +
                ", busNumber=" + busNumber +
                ", interval=" + interval +
                ", routeType=" + routeType +
                ", avgWorkload=" + avgWorkload +
                ", buses=" + buses +
                ", stops=" + stops +
                '}';
    }
}
