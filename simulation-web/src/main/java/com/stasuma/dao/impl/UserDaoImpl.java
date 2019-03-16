package com.stasuma.dao.impl;


import com.stasuma.dao.UserDao;
import com.stasuma.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
        LOGGER.trace("User successfully saved. User info: " + user);
    }

    @Override
    public User findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));
        if (user != null) {
            LOGGER.trace("User successfully found. User info: " + user);
        } else {
            LOGGER.trace("User not found");
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        if (user != null) {
            LOGGER.trace("User successfully found. User info: " + user);
        } else {
            LOGGER.trace("User not found");
        }
        return user;
    }

    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        User updUser = new User();
        updUser.setFirstName(user.getFirstName());
        updUser.setLastName(user.getLastName());
        updUser.setLogin(user.getLogin());
        updUser.setPassword(user.getPassword());
        session.update(updUser);
        LOGGER.trace("User successfully updated. User info: " + user);
    }

    @Override
    public void deleteById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user != null) {
            session.delete(user);
            LOGGER.trace("User successfully deleted");
        } else {
            LOGGER.trace("User with id " + id + " not found");
        }
    }

    @Override
    public List<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }
}
