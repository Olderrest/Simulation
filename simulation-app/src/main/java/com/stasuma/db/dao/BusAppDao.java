package com.stasuma.db.dao;

import com.stasuma.objects.Bus;
import com.stasuma.objects.Route;

import java.util.List;

public interface BusAppDao {

    void add(Bus bus, Route route);

    void update(Bus bus);

    Bus findById(int id);

    void delete();

    List<Bus> findAll();
}
