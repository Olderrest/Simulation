package com.stasuma.service.impl;

import com.stasuma.db.dao.RouteAppDao;
import com.stasuma.db.dao.impl.RouteAppDaoImpl;
import com.stasuma.objects.Route;
import com.stasuma.service.RouteAppService;

import java.util.List;

public class RouteAppServiceImpl implements RouteAppService {

    private RouteAppDao routeObjectDao = new RouteAppDaoImpl();

    @Override
    public Route add(Route route){
        return routeObjectDao.add(route);
    }

    @Override
    public void update(Route route){
        routeObjectDao.update(route);
    }

    @Override
    public List<Route> findAll(){
        return routeObjectDao.findAll();
    }
}
