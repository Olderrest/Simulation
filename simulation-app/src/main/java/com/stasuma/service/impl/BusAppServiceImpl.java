package com.stasuma.service.impl;

import com.stasuma.db.dao.BusAppDao;
import com.stasuma.db.dao.impl.BusAppDaoImpl;
import com.stasuma.objects.Bus;
import com.stasuma.objects.Route;
import com.stasuma.service.BusAppService;

import java.util.List;

public class BusAppServiceImpl implements BusAppService {

    private BusAppDao busAppDao = new BusAppDaoImpl();

    @Override
    public void add(Bus bus, Route route) {
        busAppDao.add(bus, route);
    }

    @Override
    public void update(Bus bus) {
        busAppDao.update(bus);
    }

    @Override
    public Bus findById(int id) {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Bus> findAll() {
        return null;
    }
}
