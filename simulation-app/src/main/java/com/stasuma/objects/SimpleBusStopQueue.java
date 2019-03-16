package com.stasuma.objects;


import com.stasuma.objects.Passenger;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimpleBusStopQueue {
    private Map<Passenger, Long> queue = Collections.synchronizedMap(new HashMap<>());

    public synchronized void add(Passenger passenger, int timeToWait) {
        long expireTime = TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()) + timeToWait;
        queue.put(passenger, expireTime);
    }

    public synchronized int size() {
        return queue.size();
    }

    public synchronized Passenger poll() {
        Set<Passenger> passengers = queue.keySet();
        Iterator<Passenger> iterator = passengers.iterator();
        while (iterator.hasNext()) {
            Passenger passenger = iterator.next();
            iterator.remove();
            return passenger;
        }
        return null;
    }

    public synchronized Set<Passenger> getAllPassengers() {
        return queue.keySet();
    }

    public synchronized long get(Passenger key) {
        return queue.get(key);
    }

    public synchronized void remove(Passenger passenger) {
        queue.remove(passenger);
    }

    public synchronized void removeAll() {
        queue = Collections.synchronizedMap(new HashMap<>());
    }
}
