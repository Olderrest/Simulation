package com.stasuma.dao.impl;

import com.stasuma.dao.RouteWebDao;
import com.stasuma.model.Route;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class RouteWebDaoImpl implements RouteWebDao {
    private static final Logger LOGGER = Logger.getLogger(RouteWebDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Route route) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(route);
        LOGGER.trace("Route successfully saved. Route info: " + route);
    }

    @Override
    public Route addAndReturn(Route route) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(route);
        LOGGER.trace("Route successfully saved. Route info: " + route);
        return route;
    }

    @Override
    public void update(Route route) {
        Session session = this.sessionFactory.getCurrentSession();
        Route updRoute = (Route) session.get(Route.class, new Integer(route.getId()));
        updRoute.setRouteType(route.getRouteType());
        updRoute.setStopsNumber(route.getStops().size());
        updRoute.setStops(route.getStops());
        updRoute.setBuses(route.getBuses());
        updRoute.setBusNumber(route.getBuses().size());
        updRoute.setAvgWorkload(route.getAvgWorkload());
        updRoute.setInterval(route.getInterval());
        session.save(updRoute);
        LOGGER.trace("Route successfully updated. Route info: " + updRoute);
    }

    @Override
    public Route findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Route route = (Route) session.get(Route.class, new Integer(id));
        if (route != null) {
            LOGGER.trace("Route successfully found. Route info: " + route);
            return route;
        } else {
            LOGGER.trace("Route not found");
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Route route = (Route) session.load(Route.class, new Integer(id));
        session.delete(route);
        LOGGER.trace("Route successfully deleted. Route info: " + route);
    }

    @Override
    public void deleteAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Route ");
        query.executeUpdate();
    }

    @Override
    public List<Route> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Route");
        List<Route> routes = query.list();
        for (Route route : routes) {
            Hibernate.initialize(route.getStops());
            Hibernate.initialize(route.getBuses());
        }
        return routes;
    }
}
