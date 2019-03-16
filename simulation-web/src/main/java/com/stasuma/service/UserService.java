package com.stasuma.service;

import com.stasuma.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    User findById(int id);

    User findByLogin(String login);

    void update(User user);

    void deleteById(int id);

    List<User> findAll();

    String validateLogin(User user);

    String validateRegister(User newUser);
}
