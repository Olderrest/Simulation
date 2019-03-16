package com.stasuma.db.dao;

import com.stasuma.objects.SimulationInfo;

import java.util.Set;

public interface SimulationAppInfoDao {

    void add(SimulationInfo info);

    void addAll(Set<SimulationInfo> infos);

    void deleteAll();
}
