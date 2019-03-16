package com.stasuma.db.dao;

import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;

import java.util.List;

public interface StopAppDao {
    void add(Stop stop, Route route);

    void update(Stop stop);

    Stop findById(int id);

    void deleteById(int id);

    List<Stop> findAll();
}
