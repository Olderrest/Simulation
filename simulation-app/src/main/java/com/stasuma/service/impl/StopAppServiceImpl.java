package com.stasuma.service.impl;

import com.stasuma.db.dao.StopAppDao;
import com.stasuma.db.dao.impl.StopAppDaoImpl;
import com.stasuma.objects.Route;
import com.stasuma.objects.Stop;
import com.stasuma.service.StopAppService;

import java.util.List;

public class StopAppServiceImpl implements StopAppService {

    private StopAppDao stopDao = new StopAppDaoImpl();

    @Override
    public void add(Stop stop, Route route) {
        stopDao.add(stop,route);
    }

    @Override
    public void update(Stop stop) {
        stopDao.update(stop);
    }

    @Override
    public Stop findById(int id) {
        return stopDao.findById(id);
    }

    @Override
    public List<Stop> findAll() {
        return stopDao.findAll();
    }
}
