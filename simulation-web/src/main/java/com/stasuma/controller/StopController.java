package com.stasuma.controller;

import com.stasuma.model.Route;
import com.stasuma.model.Stop;
import com.stasuma.model.User;
import com.stasuma.service.RouteWebService;
import com.stasuma.service.StopWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/stops")
public class StopController {

    private StopWebService stopWebService;
    private RouteWebService routeWebService;

    @Autowired
    public void setRouteWebService(RouteWebService routeWebService) {
        this.routeWebService = routeWebService;
    }

    @Autowired
    public void setStopWebService(StopWebService stopWebService) {
        this.stopWebService = stopWebService;
    }

    @RequestMapping(value = "/all_stops", method = RequestMethod.GET)
    public ModelAndView showAllStops(@SessionAttribute("user") User user) {
        if (user.getLogin() == null) {
            return new ModelAndView("redirect:/go_login", "user", new User());
        }
        List<Stop> stops = stopWebService.findAll();
        return new ModelAndView("stops", "stopsList", stops);
    }

    @RequestMapping(value = "/add_stop/{id}", method = RequestMethod.GET)
    public ModelAndView add(@PathVariable("id") int routeId){
        ModelAndView modelAndView = new ModelAndView("addStopToRoute");
        modelAndView.addObject("stop", new Stop());
        modelAndView.addObject("routeId", routeId);
        return modelAndView;
    }

    @RequestMapping(value = "/stop_info", method = RequestMethod.GET)
    public String showStopInfo(@RequestParam("id") int stopId, Model model) {
        Stop stop = stopWebService.findById(stopId);
        model.addAttribute("stop", stop);
        model.addAttribute("routes", stop.getRoutes());
        return "stop_info";
    }

    @RequestMapping(value = "/update_stop/{id}", method = RequestMethod.GET)
    public ModelAndView update(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("update_stop");
        Stop stop = stopWebService.findById(id);
        modelAndView.addObject("stop", stop);
        return modelAndView;
    }

    @RequestMapping(value = "/delete_stop/{id}", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable("id") int id) {
        stopWebService.deleteById(id);
        return new ModelAndView("redirect:/stops/all_stops");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("stop") Stop stop, @RequestParam("routeId") int routeId) {
        if ((stop != null) && (stop.getId() != 0)) {
            Stop existStop = stopWebService.findById(stop.getId());
            existStop.setTime(stop.getTime());
            stopWebService.update(existStop);
            return new ModelAndView("redirect:/stops/all_stops");
        } else {
            Route route = routeWebService.findById(routeId);
            stopWebService.add(stop);
            route.getStops().add(stop);
            routeWebService.update(route);
            return new ModelAndView("redirect:/routes/route_info/" + routeId);
        }
    }
}
