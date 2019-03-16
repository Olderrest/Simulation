package com.stasuma.service.impl;

import com.stasuma.dao.BusWebDao;
import com.stasuma.model.Bus;
import com.stasuma.service.BusWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("busService")
@Transactional
public class BusWebServiceImpl implements BusWebService {

    private BusWebDao busWebDao;

    @Autowired
    public void setBusWebDao(BusWebDao busWebDao) {
        this.busWebDao = busWebDao;
    }

    public void add(Bus bus){
        busWebDao.add(bus);
    }

    public void update(Bus bus){
        busWebDao.update(bus);
    }

    public Bus findById(int id){
        return busWebDao.findById(id);
    }

    public void deleteById(int id){
        busWebDao.deleteById(id);
    }

    public void deleteAll(){
        busWebDao.deleteAll();
    }

    public List<Bus> findAll(){
        return busWebDao.findAll();
    }
}
