package com.stasuma.dao;


import com.stasuma.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    User findById(int id);

    User findByLogin(String login);

    void update(User user);

    void deleteById(int id);

    List findAll();
}
