package com.stasuma.dao.impl;

import com.stasuma.dao.BusWebDao;
import com.stasuma.model.Bus;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class BusWebDaoImpl implements BusWebDao {
    private static final Logger LOGGER = Logger.getLogger(BusWebDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public void add(Bus bus) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(bus);
        LOGGER.trace("Bus successfully added. Bus info: " + bus);
    }


    @Override
    public void update(Bus bus) {
        Session session = this.sessionFactory.getCurrentSession();
        Bus updBus = (Bus) session.get(Bus.class, new Integer(bus.getId()));
        updBus.setSize(bus.getSize());
        session.save(updBus);
        LOGGER.trace("Bus successfully updated. Route info: " + updBus);
    }

    @Override
    public Bus findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Bus bus = (Bus) session.get(Bus.class, new Integer(id));
        if (bus != null) {
            LOGGER.trace("Bus successfully found. Bus info: " + bus);
            return bus;
        } else {
            LOGGER.trace("Bus not found");
            return null;
        }
    }

    @Override
    public void deleteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Bus bus = (Bus) session.load(Bus.class, new Integer(id));
        session.delete(bus);
        LOGGER.trace("Bus successfully deleted. Bus info: " + bus);
    }

    @Override
    public void deleteAll(){
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM Bus ");
        query.executeUpdate();
    }

    @Override
    public List<Bus> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Bus ");
        return query.list();
    }
}
