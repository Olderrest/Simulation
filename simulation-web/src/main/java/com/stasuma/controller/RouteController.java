package com.stasuma.controller;

import com.stasuma.model.Route;
import com.stasuma.model.User;
import com.stasuma.service.RouteWebService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/routes")
public class RouteController {


    private RouteWebService routeWebService;

    @Autowired
    public void setRouteWebService(RouteWebService routeWebService) {
        this.routeWebService = routeWebService;
    }

    @RequestMapping(value = "/all_routes", method = RequestMethod.GET)
    public ModelAndView showAllRoutes(@SessionAttribute("user") User user) {
        if (user.getLogin() == null) {
            return new ModelAndView("redirect:/go_login", "user", new User());
        }
        ModelAndView modelAndView = new ModelAndView("routes");
        List<Route> routes = routeWebService.findAll();
        modelAndView.addObject("routesList", routes);
        modelAndView.addObject("addRoute", new Route());
        return modelAndView;
    }

    @RequestMapping(value = "/route_info/{id}", method = RequestMethod.GET)
    public String showRouteInfo(@PathVariable("id") int routeId, Model model) {
        Route route = routeWebService.findById(routeId);
        initializeCollections(route);
        model.addAttribute("route", route);
        return "route_info";
    }

    @RequestMapping(value = "/update_route/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update_route");
        Route route = routeWebService.findById(id);
        initializeCollections(route);
        modelAndView.addObject("route", route);
        return modelAndView;
    }

    @RequestMapping(value = "/delete_route/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        routeWebService.deleteById(id);
        return new ModelAndView("redirect:/routes/all_routes");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("route") Route route) {
        if ((route != null) && (route.getId() != 0)) {
            Route existRoute = routeWebService.findById(route.getId());
            existRoute.setInterval(route.getInterval());
            existRoute.setRouteType(route.getRouteType());
            routeWebService.update(existRoute);
        } else {
            routeWebService.add(route);
        }
        return new ModelAndView("redirect:/routes/all_routes");
    }

    private void initializeCollections(Route route) {
        Hibernate.initialize(route.getBuses());
        Hibernate.initialize(route.getStops());
    }
}
