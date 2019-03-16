package com.stasuma.service;

import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;

import java.util.List;

public interface StopAppService {

    void add(Stop stop, Route route);

    void update(Stop stop);

    Stop findById(int id);

    List<Stop> findAll();
}
