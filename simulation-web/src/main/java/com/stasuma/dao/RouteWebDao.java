package com.stasuma.dao;


import com.stasuma.model.Route;

import java.util.List;

public interface RouteWebDao {
    void add(Route route);

    Route addAndReturn(Route route);

    void update(Route route);

    Route findById(int id);

    void deleteById(int id);

    void deleteAll();

    List<Route> findAll();
}
