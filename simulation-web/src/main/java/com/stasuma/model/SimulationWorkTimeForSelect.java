package com.stasuma.model;

import java.util.ArrayList;
import java.util.List;

public class SimulationWorkTimeForSelect {
    private List<Integer> years;
    private List<Integer> months;
    private List<Integer> days;
    private List<Integer> hours;
    private List<Integer> buses;
    private List<Integer> routes;
    private List<Integer> stops;

    public SimulationWorkTimeForSelect() {
        years = new ArrayList<>();
        months = new ArrayList<>();
        days = new ArrayList<>();
        hours = new ArrayList<>();
        buses = new ArrayList<>();
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<Integer> getMonths() {
        return months;
    }

    public void setMonths(List<Integer> months) {
        this.months = months;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }

    public List<Integer> getBuses() {
        return buses;
    }

    public void setBuses(List<Integer> buses) {
        this.buses = buses;
    }

    public List<Integer> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Integer> routes) {
        this.routes = routes;
    }

    public List<Integer> getStops() {
        return stops;
    }

    public void setStops(List<Integer> stops) {
        this.stops = stops;
    }

    @Override
    public String toString() {
        return "SimulationWorkTimeForSelect{" +
                "years=" + years +
                ", months=" + months +
                ", days=" + days +
                ", hours=" + hours +
                ", buses=" + buses +
                ", routes=" + routes +
                ", stops=" + stops +
                '}';
    }
}
