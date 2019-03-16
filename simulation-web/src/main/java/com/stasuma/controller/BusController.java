package com.stasuma.controller;

import com.stasuma.model.Bus;
import com.stasuma.model.Route;
import com.stasuma.model.User;
import com.stasuma.service.BusWebService;
import com.stasuma.service.RouteWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/buses")
public class BusController {


    private BusWebService busWebService;

    private RouteWebService routeWebService;

    @Autowired
    public void setRouteWebService(RouteWebService routeWebService) {
        this.routeWebService = routeWebService;
    }

    @Autowired
    public void setBusWebService(BusWebService busWebService) {
        this.busWebService = busWebService;
    }

    @RequestMapping(value = "/all_buses", method = RequestMethod.GET)
    public ModelAndView getAllBuses(@SessionAttribute("user") User user) {
        if (user.getLogin() == null) {
            return new ModelAndView("redirect:/go_login", "user", new User());
        }
        List<Bus> busList = busWebService.findAll();
        return new ModelAndView("buses", "busList", busList);
    }


    @RequestMapping(value = "/add_bus/{id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable("id") int routeId) {
        ModelAndView modelAndView = new ModelAndView("addBusToRoute");
        modelAndView.addObject("bus", new Bus());
        modelAndView.addObject("routeId", routeId);
        return modelAndView;
    }

    @RequestMapping(value = "/update_bus/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update_bus");
        Bus bus = busWebService.findById(id);
        modelAndView.addObject("bus", bus);
        return modelAndView;
    }

    @RequestMapping(value = "/delete_bus/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        busWebService.deleteById(id);
        return new ModelAndView("redirect:/buses/all_buses");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("bus") Bus bus, @RequestParam("routeId") int routeId) {
        if ((bus != null) && (bus.getId() != 0)) {
            Bus existBus = busWebService.findById(bus.getId());
            existBus.setSize(bus.getSize());
            busWebService.update(existBus);
            return new ModelAndView("redirect:/buses/all_buses");
        } else {
            Route route = routeWebService.findById(routeId);
            bus.setRoute(route);
            busWebService.add(bus);
            route.getBuses().add(bus);
            routeWebService.update(route);
            return new ModelAndView("redirect:/routes/route_info/" + routeId);
        }
    }

}
