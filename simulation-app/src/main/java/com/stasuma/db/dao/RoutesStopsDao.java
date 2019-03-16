package com.stasuma.db.dao;

import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;

public interface RoutesStopsDao {

    void add(Route route, Stop stop);
}
