package com.stasuma.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "buses")
public class Bus extends Model {
    @Column(name = "size")
    private int size;
    @Column(name = "load_percent")
    private int loadPercent;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public Bus() {
    }

    public Bus(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLoadPercent() {
        return loadPercent;
    }

    public void setLoadPercent(int loadPercent) {
        this.loadPercent = loadPercent;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return size == bus.size &&
                loadPercent == bus.loadPercent &&
                Objects.equals(route, bus.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, loadPercent, route);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "size=" + size +
                ", loadPercent=" + loadPercent +
                '}';
    }
}
