package com.stasuma.objects;

import java.util.Objects;

public class SimulationInfo {
    private long id;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int stopId;
    private int routeId;
    private int busId;
    private int passengersInCount;
    private int passengersOutCount;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "SimulationInfo{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", stopId=" + stopId +
                ", routeId=" + routeId +
                ", busId=" + busId +
                ", passengersInCount=" + passengersInCount +
                ", passengersOutCount=" + passengersOutCount +
                ", passengersInBusCount=" + passengersInBusCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimulationInfo info = (SimulationInfo) o;
        return id == info.id &&
                year == info.year &&
                month == info.month &&
                day == info.day &&
                hour == info.hour &&
                minute == info.minute &&
                stopId == info.stopId &&
                routeId == info.routeId &&
                busId == info.busId &&
                passengersInCount == info.passengersInCount &&
                passengersOutCount == info.passengersOutCount &&
                passengersInBusCount == info.passengersInBusCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, day, hour, minute, stopId, routeId, busId, passengersInCount, passengersOutCount, passengersInBusCount);
    }
}
