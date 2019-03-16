package com.stasuma.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "stops")
public class Stop extends Model {

    @Column(name = "time", nullable = false)
    private int time;

    @Column(name = "avg_workload")
    private int avgWorkload;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "route_stop",
            joinColumns = @JoinColumn(name = "stop_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private Set<Route> routes = new HashSet<>();

    public Stop() {
    }

    public Stop(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int distance) {
        this.time = distance;
    }

    public int getAvgWorkload() {
        return avgWorkload;
    }

    public void setAvgWorkload(int avgWorkload) {
        this.avgWorkload = avgWorkload;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + getId() +
                ", distance=" + time +
                '}';
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stop stop = (Stop) o;
        return time == stop.time &&
                avgWorkload == stop.avgWorkload &&
                Objects.equals(routes, stop.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, avgWorkload, routes);
    }
}
