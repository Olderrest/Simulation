package com.stasuma.service.impl;


import com.stasuma.dao.RouteWebDao;
import com.stasuma.model.Route;
import com.stasuma.service.RouteWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("routeService")
@Transactional
public class RouteWebServiceImpl implements RouteWebService {

    private RouteWebDao routeWebDao;

    @Autowired
    public void setRouteWebDao(RouteWebDao routeWebDao) {
        this.routeWebDao = routeWebDao;
    }

    public void add(Route route) {
        routeWebDao.add(route);
    }

    public Route addAndReturn(Route route){
        return routeWebDao.addAndReturn(route);
    }

    public void update(Route route) {
        routeWebDao.update(route);
    }


    public Route findById(int id) {
        return routeWebDao.findById(id);
    }


    public void deleteById(int id) {
        routeWebDao.deleteById(id);
    }

    public void deleteAll(){
        routeWebDao.deleteAll();
    }

    public List<Route> findAll() {
        return routeWebDao.findAll();
    }
}
