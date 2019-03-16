package com.stasuma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "simulation_work_time")
public class SimulationWorkTimeWeb extends Model {
    @Column(name = "years")
    private int year;
    @Column(name = "months")
    private int month;
    @Column(name = "days")
    private int day;
    @Column(name = "start_time")
    private String startSimulationTime;
    @Column(name = "end_time")
    private String endSimulationTime;
    @Column(name = "route_count")
    private int routeCount;
    @Column(name = "bus_count")
    private int busCount;
    @Column(name = "stop_count")
    private int stopCount;

    public SimulationWorkTimeWeb() {
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

    public String getStartSimulationTime() {
        return startSimulationTime;
    }

    public void setStartSimulationTime(String startSimulationTime) {
        this.startSimulationTime = startSimulationTime;
    }

    public String getEndSimulationTime() {
        return endSimulationTime;
    }

    public void setEndSimulationTime(String endSimulationTime) {
        this.endSimulationTime = endSimulationTime;
    }

    public int getRouteCount() {
        return routeCount;
    }

    public void setRouteCount(int routeCount) {
        this.routeCount = routeCount;
    }

    public int getBusCount() {
        return busCount;
    }

    public void setBusCount(int busCount) {
        this.busCount = busCount;
    }

    public int getStopCount() {
        return stopCount;
    }

    public void setStopCount(int stopCount) {
        this.stopCount = stopCount;
    }

    @Override
    public String toString() {
        return "SimulationWorkTimeWeb{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", startSimulationTime='" + startSimulationTime + '\'' +
                ", endSimulationTime='" + endSimulationTime + '\'' +
                ", routeCount=" + routeCount +
                ", busCount=" + busCount +
                ", stopCount=" + stopCount +
                '}';
    }
}
