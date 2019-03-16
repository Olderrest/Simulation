package com.stasuma.dao;

import com.stasuma.model.Bus;

import java.util.List;

public interface BusWebDao {

    void add(Bus bus);

    void update(Bus bus);

    Bus findById(int id);

    void deleteById(int id);

    void deleteAll();

    List<Bus> findAll();
}
