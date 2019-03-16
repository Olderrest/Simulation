package com.stasuma.objects;

import java.util.Objects;

public class Passenger {
    private int requiredStopNumber;
    private static long idGenerator;
    private long id;

    public Passenger(int requiredStopNumber) {
        this.id = idGenerator++;
        this.requiredStopNumber = requiredStopNumber;
    }

    public synchronized int getRequiredStopNumber() {
        return requiredStopNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return requiredStopNumber == passenger.requiredStopNumber &&
                id == passenger.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requiredStopNumber, id);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "requiredStopNumber=" + requiredStopNumber +
                ", id=" + id +
                '}';
    }
}
