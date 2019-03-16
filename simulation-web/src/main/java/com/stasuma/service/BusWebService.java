package com.stasuma.service;

import com.stasuma.model.Bus;

import java.util.List;

public interface BusWebService {

    void add(Bus bus);

    void update(Bus bus);

    Bus findById(int id);

    void deleteById(int id);

    void deleteAll();

    List<Bus> findAll();
}
