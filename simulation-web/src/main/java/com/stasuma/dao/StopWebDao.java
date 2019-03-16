package com.stasuma.dao;


import com.stasuma.model.Stop;

import java.util.List;

public interface StopWebDao {
    void add(Stop stop);

    void update(Stop stop);

    Stop findById(int id);

    void deleteById(int id);

    void deleteAll();

    List<Stop> findAll();
}
