package com.stasuma.service;

import com.stasuma.objects.Route;

import java.util.List;

public interface RouteAppService {

    Route add(Route route);

    void update(Route route);

    List<Route> findAll();
}
