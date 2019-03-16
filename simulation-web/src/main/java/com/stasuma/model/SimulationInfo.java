package com.stasuma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "simulation_info")
public class SimulationInfo extends Model {
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month;
    @Column(name = "day")
    private int day;
    @Column(name = "hour")
    private int hour;
    @Column(name = "minute")
    private int minute;
    @Column(name = "stop_id")
    private int stopId;
    @Column(name = "route_id")
    private int routeId;
    @Column(name = "bus_id")
    private int busId;
    @Column(name = "passengers_in")
    private int passengersInCount;
    @Column(name = "passengers_out")
    private int passengersOutCount;
    @Column(name = "passengers_in_bus")
    private int passengersInBusCount;

    public SimulationInfo() {
    }

    public SimulationInfo(int year, int month, int day, int hour, int minute, int stopId, int routeId, int busId, int passengersInCount, int passengersOutCount, int passengersInBusCount) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.stopId = stopId;
        this.routeId = routeId;
        this.busId = busId;
        this.passengersInCount = passengersInCount;
        this.passengersOutCount = passengersOutCount;
        this.passengersInBusCount = passengersInBusCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getStopId() {
        return stopId;
    }

    public void setStopId(int stopId) {
        this.stopId = stopId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getPassengersInCount() {
        return passengersInCount;
    }

    public void setPassengersInCount(int passengersInCount) {
        this.passengersInCount = passengersInCount;
    }

    public int getPassengersOutCount() {
        return passengersOutCount;
    }

    public void setPassengersOutCount(int passengersOutCount) {
        this.passengersOutCount = passengersOutCount;
    }

    public int getPassengersInBusCount() {
        return passengersInBusCount;
    }

    public void setPassengersInBusCount(int passengersInBusCount) {
        this.passengersInBusCount = passengersInBusCount;
    }
}
