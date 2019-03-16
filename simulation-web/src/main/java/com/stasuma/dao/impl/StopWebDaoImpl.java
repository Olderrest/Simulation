package com.stasuma.dao.impl;


import com.stasuma.dao.StopWebDao;
import com.stasuma.model.Stop;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class StopWebDaoImpl implements StopWebDao {
    private static final Logger LOGGER = Logger.getLogger(StopWebDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(Stop stop) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(stop);
        LOGGER.trace("Stop successfully saved. Stop info: " + stop);
    }

    @Override
    public void update(Stop stop) {
        Session session = this.sessionFactory.getCurrentSession();
        Stop updStop = (Stop) session.get(Stop.class, new Integer(stop.getId()));
        updStop.setTime(stop.getTime());
        updStop.setAvgWorkload(stop.getAvgWorkload());
        session.save(updStop);
        LOGGER.trace("Stop successfully updated. Stop info: " + stop);
    }

    @Override
    public Stop findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Stop stop = (Stop) session.get(Stop.class, new Integer(id));
        Hibernate.initialize(stop.getRoutes());
        if (stop != null) {
            LOGGER.trace("Stop successfully found. Stop info: " + stop);
            return stop;
        } else {
            LOGGER.trace("Stop not found");
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Stop stop = (Stop) session.get(Stop.class, new Integer(id));
        session.delete(stop);
        LOGGER.trace("Stop successfully deleted. Stop info: " + stop);
    }

    @Override
    public void deleteAll(){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM  Stop");
        query.executeUpdate();
    }

    @Override
    public List<Stop> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Stop");
        return query.list();
    }

//    @Override
//    public List<Route> findAllRoutes(Stop stop) {
//        Session session = this.sessionFactory.getCurrentSession();
//        String sql = "SELECT * FROM route_stop WHERE stop_id = :id";
//        Query query = session.createSQLQuery(sql);
//        query.setParameter("id", stop.getId());
//        List<Route> routes = query.list();
//        return query.list();
//    }
}
