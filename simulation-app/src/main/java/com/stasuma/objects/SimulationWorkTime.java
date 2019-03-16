package com.stasuma.objects;

public class SimulationWorkTime {
    private int id;
    private int years;
    private int months;
    private int days;
    private String startSimulation;
    private String endSimulation;
    private int routeCount;
    private int busCount;
    private int stopCount;

    public SimulationWorkTime() {
    }

    public SimulationWorkTime(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getStartSimulation() {
        return startSimulation;
    }

    public void setStartSimulation(String startSimulation) {
        this.startSimulation = startSimulation;
    }

    public String getEndSimulation() {
        return endSimulation;
    }

    public void setEndSimulation(String endSimulation) {
        this.endSimulation = endSimulation;
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
}
