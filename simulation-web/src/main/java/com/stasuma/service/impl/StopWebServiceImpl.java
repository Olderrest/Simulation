package com.stasuma.service.impl;

import com.stasuma.dao.StopWebDao;
import com.stasuma.model.Stop;
import com.stasuma.service.StopWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("stopService")
@Transactional
public class StopWebServiceImpl implements StopWebService {

    private StopWebDao stopWebDao;

    @Autowired
    public void setStopWebDao(StopWebDao stopWebDao) {
        this.stopWebDao = stopWebDao;
    }

    public void add(Stop stop) {
        stopWebDao.add(stop);
    }

    public void update(Stop stop) {
        stopWebDao.update(stop);
    }

    public Stop findById(int id) {
        return stopWebDao.findById(id);
    }

    public void deleteById(int id) {
        stopWebDao.deleteById(id);
    }

    public void deleteAll() {
        stopWebDao.deleteAll();
    }

    public List<Stop> findAll() {
        return stopWebDao.findAll();
    }

}
