package com.stasuma.db.dao;

import com.stasuma.objects.Route;

import java.util.List;

public interface RouteAppDao {
    Route add(Route route);

    void update(Route route);

    Route findById(int id);

    void deleteById(int id);

    List<Route> findAll();
}
