package com.stasuma.service.impl;

import com.stasuma.dao.UserDao;
import com.stasuma.model.User;
import com.stasuma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add(User user) {
        userDao.add(user);
    }

    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public String validateLogin(User user) {
        User checkUser = userDao.findByLogin(user.getLogin());
        if (checkUser == null) {
            return "Login/password is incorrect";
        } else if (!user.getLogin().equals(checkUser.getLogin()) || !user.getPassword().equals(checkUser.getPassword())) {
            return "Login/password is incorrect";
        }
        return null;
    }

    public String validateRegister(User newUser) {
        List<User> users = userDao.findAll();
        for (User user : users) {
            if (user.getLogin().equals(newUser.getLogin())) {
                return "That login already exist";
            }
        }
        return null;
    }
}
