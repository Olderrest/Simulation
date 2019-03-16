package com.stasuma.service;

import com.stasuma.objects.SimulationInfo;

import java.util.Set;

public interface SimulationAppInfoService {

    void add(SimulationInfo info);

    void addAll(Set<SimulationInfo> infos);

    void deleteAll();
}
