package com.stasuma.objects;

import java.util.Objects;

public class Stop implements Comparable<Stop>{
    private int id;
    private int time;
    private volatile SimpleBusStopQueue queue;
    private long averageWorkload;
    private long workloadCount;

    public Stop() {
        queue = new SimpleBusStopQueue();
    }

    public Stop(int time) {
        this.time = time;
        queue = new SimpleBusStopQueue();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getAverageWorkload() {
        return averageWorkload;
    }

    public void setAverageWorkload(long averageWorkload) {
        this.averageWorkload = averageWorkload;
    }

    public synchronized SimpleBusStopQueue getQueue() {
        return queue;
    }

    public void increaseWorkload(int passengers){
        workloadCount++;
        averageWorkload += passengers;
    }

    public void calculateWorkload(){
        averageWorkload = averageWorkload / workloadCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stop stop = (Stop) o;

        if (time != stop.time) return false;
        return Objects.equals(queue, stop.queue);
    }

    @Override
    public int hashCode() {
        int result = time;
        result = 31 * result + (queue != null ? queue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Stop{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(Stop o) {
        return Integer.compare(getId(), o.getId());
    }
}
