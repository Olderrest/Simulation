package com.stasuma.model;

public class SimulationInfoQuery {
    private int yearFrom;
    private int yearUntil;
    private int monthFrom;
    private int monthUntil;
    private int dayFrom;
    private int dayUntil;
    private int hourFrom;
    private int hourUntil;
    private int busIdFrom;
    private int busIdUntil;
    private int routeIdFrom;
    private int routeIdUntil;
    private int stopIdFrom;
    private int stopIdUntil;

    public SimulationInfoQuery() {
        yearFrom = 0;
        yearUntil = 0;
        monthFrom = 0;
        monthUntil = 0;
        dayFrom = 0;
        dayUntil = 0;
        hourFrom = 0;
        hourUntil = 0;
        busIdFrom = 0;
        busIdUntil = 0;
        routeIdFrom = 0;
        routeIdUntil = 0;
        stopIdFrom = 0;
        stopIdUntil = 0;
    }

    public int getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(int yearFrom) {
        this.yearFrom = yearFrom;
    }

    public int getYearUntil() {
        return yearUntil;
    }

    public void setYearUntil(int yearUntil) {
        this.yearUntil = yearUntil;
    }

    public int getMonthFrom() {
        return monthFrom;
    }

    public void setMonthFrom(int monthFrom) {
        this.monthFrom = monthFrom;
    }

    public int getMonthUntil() {
        return monthUntil;
    }

    public void setMonthUntil(int monthUntil) {
        this.monthUntil = monthUntil;
    }

    public int getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(int dayFrom) {
        this.dayFrom = dayFrom;
    }

    public int getDayUntil() {
        return dayUntil;
    }

    public void setDayUntil(int dayUntil) {
        this.dayUntil = dayUntil;
    }

    public int getHourFrom() {
        return hourFrom;
    }

    public void setHourFrom(int hourFrom) {
        this.hourFrom = hourFrom;
    }

    public int getHourUntil() {
        return hourUntil;
    }

    public void setHourUntil(int hourUntil) {
        this.hourUntil = hourUntil;
    }

    public int getBusIdFrom() {
        return busIdFrom;
    }

    public void setBusIdFrom(int busIdFrom) {
        this.busIdFrom = busIdFrom;
    }

    public int getBusIdUntil() {
        return busIdUntil;
    }

    public void setBusIdUntil(int busIdUntil) {
        this.busIdUntil = busIdUntil;
    }

    public int getRouteIdFrom() {
        return routeIdFrom;
    }

    public void setRouteIdFrom(int routeIdFrom) {
        this.routeIdFrom = routeIdFrom;
    }

    public int getRouteIdUntil() {
        return routeIdUntil;
    }

    public void setRouteIdUntil(int routeIdUntil) {
        this.routeIdUntil = routeIdUntil;
    }

    public int getStopIdFrom() {
        return stopIdFrom;
    }

    public void setStopIdFrom(int stopIdFrom) {
        this.stopIdFrom = stopIdFrom;
    }

    public int getStopIdUntil() {
        return stopIdUntil;
    }

    public void setStopIdUntil(int stopIdUntil) {
        this.stopIdUntil = stopIdUntil;
    }

    @Override
    public String toString() {
        return "SimulationInfoQuery{" +
                "yearFrom=" + yearFrom +
                ", yearUntil=" + yearUntil +
                ", monthFrom=" + monthFrom +
                ", monthUntil=" + monthUntil +
                ", dayFrom=" + dayFrom +
                ", dayUntil=" + dayUntil +
                ", hourFrom=" + hourFrom +
                ", hourUntil=" + hourUntil +
                ", busIdFrom=" + busIdFrom +
                ", busIdUntil=" + busIdUntil +
                ", routeIdFrom=" + routeIdFrom +
                ", routeIdUntil=" + routeIdUntil +
                ", stopIdFrom=" + stopIdFrom +
                ", stopIdUntil=" + stopIdUntil +
                '}';
    }
}
